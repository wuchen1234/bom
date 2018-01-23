package zhth.bom.management.bom.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import zhth.bom.management.bom.domian.BomMaterialItem;
import zhth.bom.management.bom.domian.BuildingTable;
import zhth.bom.management.bom.domian.StockMaterialItem;
import zhth.bom.management.bom.domian.User;
import zhth.bom.management.bom.domianextend.BomMaterialItemExtend;
import zhth.bom.management.bom.repository.BuildingTableRepository;
import zhth.bom.management.bom.service.serivceImpl.BomMaterialItemServiceImpl;
import zhth.bom.management.bom.service.serivceImpl.BomMaterialServiceImpl;
import zhth.bom.management.bom.service.serivceImpl.UserServiceImpl;
import zhth.bom.management.bom.util.change.ObjectUtils;
import zhth.bom.management.bom.util.change.Serial;
import zhth.bom.management.bom.util.change.SpellChenge;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Api(value = "查找楼号",description = "查找楼号" ,consumes = "rer")
@RestController
@RequestMapping(value = "/")
public class RestC{

    @Autowired
    private BuildingTableRepository buildingTableRepository;

    @Autowired
    private BomMaterialServiceImpl bomMaterialService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private BomMaterialItemServiceImpl bomMaterialItemService;


    @InitBinder
    public void initBind(WebDataBinder binder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }


    @ApiOperation(value = "查找楼号", notes = "查找楼号", httpMethod = "POST")
    @RequestMapping(value={"/querybuilding"},method = RequestMethod.POST)
    public  String getQuerybuildingt(String name) {
        List<BuildingTable> buildingTable=null;
        try {
            name="zhth"+name.trim();
            System.out.println(name);
            SpellChenge toping=new  SpellChenge(name.trim());
            buildingTable= buildingTableRepository.findBybt(toping.toPingying());
          }catch (Exception e){
                e.printStackTrace();
          }
            return    JSONObject.toJSONString(buildingTable);
    }

    @ApiOperation(value = "查找进货材料清单", notes = "查找进货材料清单", httpMethod = "POST")
    @RequestMapping(value={"/materialItem"},method = RequestMethod.POST)
    public String  materialItem(String search,String  select) {
        SpellChenge t = new SpellChenge(search);
        String g = "zhth"+t.toPingying();
        List<StockMaterialItem> bStockMaterialItemList = bomMaterialService.finbyname(g.trim(), select);
        if (bStockMaterialItemList.size() == 0) {
            bStockMaterialItemList = bomMaterialService.matchpart(g, select);
        }
        return JSONObject.toJSONString(bStockMaterialItemList);
        }


    @ApiOperation(value = "添加用户", notes = "添加用户", httpMethod = "POST")
     @RequestMapping(value = {"/adduser"},method = RequestMethod.POST,produces ="application/json")
    public String adduser(@RequestBody() User user){
          String  flag="0";
          User user1=null;
         try{
             user.setSex("男");
             user.setState('1');
             user.setTime(new Date());
             user1= userService.save(user);
             if(ObjectUtils.isNotEmpty(user1)){
                 flag="1";
             }
         } catch (Exception e) {
                e.printStackTrace();
        }
          return  flag;
     }


     @RequestMapping(value={"/orderquery"},method = RequestMethod.POST)
      public  String orderquery(String name,String louhao){
         List<BomMaterialItem> bomMaterialItems=null;
         List<BomMaterialItemExtend> bomMaterialItemExtends=null;
        try{
            if(name.equals("")&&louhao.equals("")){
                bomMaterialItems= bomMaterialItemService.findAll();
            }else {
                if((!name.equals(""))&&louhao.equals("")){
                    bomMaterialItems=bomMaterialItemService.findByBmiPerson(name);
                }else if((name.equals(""))&&(!louhao.equals(""))){
                    bomMaterialItems=bomMaterialItemService.findByBmiExplain(louhao);
                }else{
                    bomMaterialItems=bomMaterialItemService.findByBmiExplainAndAndBmiPerson(louhao,name);
                }
            }
            bomMaterialItemExtends=new ArrayList<BomMaterialItemExtend>();
            for (BomMaterialItem b: bomMaterialItems) {
                BomMaterialItemExtend bomMaterialItemExtend=new BomMaterialItemExtend();
                bomMaterialItemExtend.setPerson(b.getBmiPerson());
                bomMaterialItemExtend.setExplain(b.getBmiExplain());
                bomMaterialItemExtend.setTime(Serial.serialTime(b.getBmiTime()));
                bomMaterialItemExtend.setTowernum(b.getBuildingTable().getBtName());
                bomMaterialItemExtend.setRemark(b.getBmiRemark());
                bomMaterialItemExtend.setSerial(b.getSerialNum());
                bomMaterialItemExtends.add(bomMaterialItemExtend);
            }
        }catch (Exception e){
                    e.printStackTrace();
        }
         return    JSONObject.toJSONString(bomMaterialItemExtends);

     }
  }
