package zhth.bom.management.bom.service.serivceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Service;
import zhth.bom.management.bom.domian.*;
import zhth.bom.management.bom.domianextend.ItemTemporary;
import zhth.bom.management.bom.repository.PartsListRepository;
import zhth.bom.management.bom.repository.StockMaterialItemRepository;
import zhth.bom.management.bom.util.change.ObjectUtils;
import zhth.bom.management.bom.util.change.WayConst;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * 查询物料清单的实现
 */

@Service
public class BomMaterialServiceImpl {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PartsListRepository partsListRepository;

    @Autowired
    private StockMaterialItemRepository stockMaterialItemRepository;


    /**
     * 查询物料清单表
     *
     * @param name
     * @param p
     * @param start
     * @param pageSize
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<BomMaterial> queryForList(String name, String p, int start, int pageSize) {
        String sql = "select * from " + name + " where serialNum=? limit ?,?";
        return (List<BomMaterial>) jdbcTemplate.query(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, p);
                ps.setInt(2, (start - 1) * pageSize);
                ps.setInt(3, pageSize);
            }
        }, new ResultSetExtractor<List<BomMaterial>>() {
            @Override
            public List<BomMaterial> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<BomMaterial> list = new ArrayList<BomMaterial>();
                while (rs.next()) {
                    BomMaterial bomMaterial = new BomMaterial();
                    bomMaterial.setBmId(rs.getInt(1));
                    bomMaterial.setBmModel(rs.getString(2));
                    bomMaterial.setBmNum(rs.getInt(3));
                    bomMaterial.setSerialNum(rs.getString(4));
                    bomMaterial.setBmRemark(rs.getString(5));
                    list.add(bomMaterial);
                }
                return list;
            }
        });
    }

    public List<BomMaterial> queryForList(String name, String p) {
        String sql = "select * from " + name + " where serialNum=?";
        return (List<BomMaterial>) jdbcTemplate.query(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, p);
            }
        }, new ResultSetExtractor<List<BomMaterial>>() {
            @Override
            public List<BomMaterial> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<BomMaterial> list = new ArrayList<BomMaterial>();
                while (rs.next()) {
                    BomMaterial bomMaterial = new BomMaterial();
                    bomMaterial.setBmId(rs.getInt(1));
                    bomMaterial.setBmModel(rs.getString(2));
                    bomMaterial.setBmNum(rs.getInt(3));
                    bomMaterial.setSerialNum(rs.getString(4));
                    bomMaterial.setBmRemark(rs.getString(5));
                    list.add(bomMaterial);
                }
                return list;
            }
        });
    }

    public List<StockMaterialItem> matchpart(String name, String p, int way) {
        //通过当前楼号序列号查出所所需物料清单
        List<BomMaterial> bomlist = queryForList(name, p);

        List<PartsList> partsListList = getPartsList(bomlist);

        List<ItemTemporary> itemTempA = new ArrayList<ItemTemporary>();
        for (PartsList partsList : partsListList) {
            System.out.println(partsList.getSnum()+"---"+partsList.getModel());
            Set<PartsItem> PI = partsList.getPartsItems();// 总的set集合 零件用材明细表
            if(ObjectUtils.isNotEmpty(PI)){
                int num = partsList.getSnum();
                Iterator<PartsItem> it = PI.iterator();
                while (it.hasNext()) {
                    ItemTemporary itemTemporary = new ItemTemporary();
                    PartsItem pi = it.next();
                    MaterialList materialList = pi.getMeterialList();
                    itemTemporary.setBigDecimal(pi.getPiLength());
                    itemTemporary.setSum(pi.getPiNum() * num);//同一种材料会获取他的总的数量
                    itemTemporary.setMaterialList(materialList);
                    itemTempA.add(itemTemporary);
                }
            }
        }
        List<ItemTemporary> itemTempB = getwithItem(itemTempA);

      /*  Set<ItemTemporary> itemTempC = new HashSet<ItemTemporary>();*/
        Set<ItemTemporary> itemTempD = new HashSet<ItemTemporary>();
        for (ItemTemporary item : itemTempB) {
            String notename = item.getMaterialList().getMateCode();
            Boolean flag =false;
            for (ItemTemporary it: itemTempD){
                if(notename.equals(it.getMaterialList().getMateCode())){
                    BigDecimal bigDecimal = item.getBigDecimal();
                    bigDecimal = bigDecimal.add(item.getMaterialList().getSawKerf());
                    BigDecimal bg[];
                    BigDecimal length = item.getMaterialList().getMateLength();
                    bg=length.divideAndRemainder(bigDecimal);
                    int sum=bg[0].intValue();
                    BigDecimal sung=bg[1];
                    int sumx=0;
                    if(ObjectUtils.isNotEmpty(it.getRemain())){//剩余不等于空
                        BigDecimal bg1[]=it.getRemain().divideAndRemainder(bigDecimal);
                        sumx=bg1[0].intValue();
                    }
                    if(item.getSum()-sumx<0){
                        //所需长度小于剩余长度
                        int s=sumx-item.getSum();
                        BigDecimal decimal=bigDecimal.multiply(new BigDecimal(s));
                        it.setRemain(decimal.add(sung));
                    }else if((item.getSum()-sumx)%sum==0){
                        int getsum=it.getSum();
                        getsum+=(item.getSum()-sumx)/sum;
                        it.setSum(getsum);
                    }else{
                        int remainder=(item.getSum()-sumx)%sum;//取余胜多少
                        BigDecimal decimal=bigDecimal.multiply(new BigDecimal(remainder));
                        it.setRemain(decimal.add(sung));//剩下多少
                        int  getsum=it.getSum();
                        getsum+=item.getSum()/sum+1;
                        it.setSum(getsum);
                    }
                    flag=true;
                }
            }
            if(!flag){
                BigDecimal bigDecimal = item.getBigDecimal();
                bigDecimal = bigDecimal.add(item.getMaterialList().getSawKerf());
                BigDecimal length = item.getMaterialList().getMateLength();
                length = length.subtract(item.getMaterialList().getSawHead());
                BigDecimal bg[]=length.divideAndRemainder(bigDecimal);
                int sum=bg[0].intValue();
                BigDecimal sung=bg[1];
                if(item.getSum() % sum == 0) {
                    item.setSum(item.getSum() / sum);
                    //剩下的
                    item.setRemain(sung);
                } else {
                    int remainder=item.getSum()%sum;//取余胜多少
                    BigDecimal decimal=bigDecimal.multiply(new BigDecimal(remainder));
                    item.setRemain(decimal.add(sung));//剩下多少必须加上一根
                    item.setSum(item.getSum()/sum+1);
                }
                itemTempD.add(item);
            }
           /* BigDecimal bigDecimal = item.getBigDecimal();
            bigDecimal = bigDecimal.add(item.getMaterialList().getSawKerf());
            BigDecimal length = item.getMaterialList().getMateLength();
            System.out.println("长度"+length);
            length = length.subtract(item.getMaterialList().getSawHead());
            int sum = length.divide(bigDecimal, 1).intValue();
            System.out.println("根数"+sum);
            if (item.getSum() % sum == 0) {
                item.setSum(item.getSum() / sum);
            } else {
                item.setSum(item.getSum() / sum + 1);
                System.out.println(item.getSum());
                System.out.println(sum);
                System.out.println(item.getSum()/sum+1);
            }*/
        }
        //合并同材料的
      /*  for (ItemTemporary item : itemTempB) {
            String notename = item.getMaterialList().getMateCode();
            int sum = 0;
            Boolean flag = true;
            for (ItemTemporary it : itemTempB) {
                if (notename.equals(it.getMaterialList().getMateCode())) {
                    sum += it.getSum();
                }
            }
            for (ItemTemporary it : itemTempC) {
                if (item.getMaterialList().getMateCode().equals(it.getMaterialList().getMateCode())) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                ItemTemporary itemTemporary = new ItemTemporary();
                itemTemporary.setSum(sum);
                itemTemporary.setMaterialList(item.getMaterialList());
                itemTemporary.setBigDecimal(item.getBigDecimal());
                itemTempC.add(itemTemporary);
            }
        }*/
        for (ItemTemporary ccc : itemTempD) {
            StockMaterialItem stockMaterialItem = new StockMaterialItem();
            stockMaterialItem.setMateName(ccc.getMaterialList().getMateCode());
            stockMaterialItem.setBtName(name);
            stockMaterialItem.setSerialNum(p);
            stockMaterialItem.setBmiRemark(ccc.getMaterialList().getMateName());
            stockMaterialItem.setQuantum(ccc.getMaterialList().getLineDensity().multiply(ccc.getMaterialList().getMateLength()).multiply(new BigDecimal(ccc.getSum())));
            stockMaterialItem.setSmiNum(ccc.getSum());
            stockMaterialItem.setSmiTime(new Date());
            stockMaterialItem.setWay(WayConst.MATCHPART);
            stockMaterialItemRepository.save(stockMaterialItem);
            //存到所需材料表中
        }
        return finbyname(name, p, way);
    }

    /**
     * 查询楼栋材料进货清单
     *
     * @param name
     * @param p
     * @return
     */
    public List<StockMaterialItem> finbyname(String name, String p, int way) {
        return stockMaterialItemRepository.findbyname(name, p, way);
    }

    /**
     * 计算单位总物料长度，除以单根物料长度，求出最小物料
     *
     * @param name
     * @param p
     * @return List<StockMaterialItem>
     */
    public List<StockMaterialItem> minmaterial(String name, String p, int way) {
        /**
         * 查询当次零件
         */
        List<BomMaterial> bomlist = queryForList(name, p);

        List<PartsList> partsLists = getPartsList(bomlist);

        List<ItemTemporary> itemTempA = new ArrayList<ItemTemporary>();

        for (PartsList partsList : partsLists) {

            //打印获取零件根数和零件种类
            System.out.println(partsList.getSnum() + "-----" + partsList.getModel());

            Set<PartsItem> PItem = partsList.getPartsItems();// 总的set集合 零件用材明细表

            if (ObjectUtils.isNotEmpty(PItem)) {
                int num = partsList.getSnum();
                Iterator<PartsItem> itpart = PItem.iterator();
                /**
                 * 获取一种零件所使用的型材和当前型材的长度数量
                 */
                while (itpart.hasNext()) {
                    ItemTemporary itemTemporary = new ItemTemporary();
                    PartsItem pi = itpart.next();
                    MaterialList materialList = pi.getMeterialList();
                    //设置材料的总长度
                    itemTemporary.setBigDecimal(pi.getPiLength());
                    /**
                     * 同一种材料会获取他的总的数量
                     * 同一种材料可能使用到同一种材料多次
                     */
                    itemTemporary.setSum(pi.getPiNum() * num);
                    itemTemporary.setMaterialList(materialList);
                    itemTempA.add(itemTemporary);
                }
            }
        }

        List<ItemTemporary> itemTempB = getwithItem(itemTempA);

        Set<ItemTemporary> itemTempC = new HashSet<ItemTemporary>();

        for (ItemTemporary item : itemTempB) {
            String notename = item.getMaterialList().getMateCode();
            Boolean flag = false;
            for (ItemTemporary itemgo : itemTempC) {
                if (notename.equals(itemgo.getMaterialList().getMateCode())) {
                    //材料长度加上据缝
                    BigDecimal bg = itemgo.getBigDecimal();
                    BigDecimal bigDecimal = item.getBigDecimal().add(item.getMaterialList().getSawKerf());
                    bg = bg.add(bigDecimal.multiply(new BigDecimal(item.getSum())));
                    itemgo.setBigDecimal(bg);
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                BigDecimal bigDecimal = item.getBigDecimal();
                bigDecimal = bigDecimal.add(item.getMaterialList().getSawKerf());
                bigDecimal = bigDecimal.multiply(new BigDecimal(item.getSum()));
                item.setBigDecimal(bigDecimal);
                itemTempC.add(item);
            }
        }

        for (ItemTemporary item : itemTempC) {
            BigDecimal length = item.getMaterialList().getMateLength();
            length = length.subtract(item.getMaterialList().getSawHead());
            BigDecimal bg[] = item.getBigDecimal().divideAndRemainder(length);
            int sum = bg[0].intValue();
            double sum2 = bg[1].doubleValue();
            //只要总长度除以材料长度不为0 就增加一跟材料
            if (sum2 > 0) {
                item.setSum(sum + 1);
            }
        }

        for (ItemTemporary item : itemTempC) {
            StockMaterialItem stockMaterialItem = new StockMaterialItem();
            stockMaterialItem.setMateName(item.getMaterialList().getMateCode());
            stockMaterialItem.setBtName(name);
            stockMaterialItem.setSerialNum(p);
            stockMaterialItem.setBmiRemark(item.getMaterialList().getMateName());
            stockMaterialItem.setQuantum(item.getMaterialList().getLineDensity().multiply(item.getMaterialList().getMateLength()).multiply(new BigDecimal(item.getSum())));
            stockMaterialItem.setSmiNum(item.getSum());
            stockMaterialItem.setSmiTime(new Date());
            stockMaterialItem.setWay(WayConst.MINMATERIA);
            stockMaterialItemRepository.save(stockMaterialItem);
            //存到所需材料表中
        }
        return finbyname(name, p, way);
    }

    private List<PartsList> getPartsList(List<BomMaterial> bomlist) {

        List<PartsList> partsListList = new LinkedList<PartsList>();
        /**
         * 循环当次零件
         */
        for (BomMaterial c : bomlist) {
            PartsList partsList = partsListRepository.findByModel(c.getBmModel());
            if (ObjectUtils.isNotEmpty(partsList)) {
                //当前物料清单表数量存入标准零件库表中
                System.out.println(c.getBmModel() + "-----名字");
                System.out.println(c.getBmNum() + "=====数量");
                partsList.setSnum(c.getBmNum());
                partsListList.add(partsList);
            } else {
                //如果之前存在的零件就合并
                Boolean flagT = false;
                for (PartsList g : partsListList) {
                    if (g.getModel().equals(c.getBmRemark())) {
                        int guim = g.getSnum();
                        guim += c.getBmNum();
                        g.setSnum(guim);
                        flagT = true;
                        break;
                    }
                }
                //不存在就添加在下一列
                if (!flagT) {
                    PartsList remark = partsListRepository.findByModel(c.getBmRemark());
                    if (ObjectUtils.isNotEmpty(remark)) {
                        System.out.println(c.getBmRemark() + "-----名字");
                        System.out.println(c.getBmNum() + "///数量");
                        remark.setSnum(c.getBmNum());
                        partsListList.add(remark);
                    } else {
                        //解析
                        /**
                         * 通过零件名字自动计算使用什么材料
                         * 暂时还没写
                         */

                    }
                }
            }
        }
        return partsListList;
    }

    /**
     * 合并同种材料与同长度
     */
    private List<ItemTemporary> getwithItem(List<ItemTemporary> itemq) {
        List<ItemTemporary> itemTempB = new ArrayList<ItemTemporary>();

        for (ItemTemporary item : itemq) {
            String nateName = item.getMaterialList().getMateCode();
            BigDecimal bigDecimal = item.getBigDecimal();
            Boolean flag = false;
            //合并同长度同类别的材料数量
            for (ItemTemporary it : itemTempB) {
                if (nateName.equals(it.getMaterialList().getMateCode()) && (bigDecimal.compareTo(it.getBigDecimal()) == 0)) {
                    int sumg = it.getSum();
                    sumg += item.getSum();
                    System.out.println(sumg + "--" + it.getMaterialList().getMateCode());
                    it.setSum(sumg);
                    flag = true;
                    break;
                }
            }
            //去掉相同类别相同长度的材料
            if (!flag) {
                System.out.println(item.getSum() + "/" + item.getMaterialList().getMateCode() + "/" + item.getBigDecimal());
                itemTempB.add(item);
            }
        }
        return itemTempB;
    }
}

