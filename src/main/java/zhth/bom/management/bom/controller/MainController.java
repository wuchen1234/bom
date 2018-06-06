package zhth.bom.management.bom.controller;



import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import zhth.bom.management.bom.domian.BomMaterialItem;
import zhth.bom.management.bom.domian.BuildingTable;
import zhth.bom.management.bom.domian.StockMaterialItem;
import zhth.bom.management.bom.domian.User;
import zhth.bom.management.bom.service.serivceImpl.BomMaterialItemServiceImpl;
import zhth.bom.management.bom.service.serivceImpl.BomMaterialServiceImpl;
import zhth.bom.management.bom.service.serivceImpl.UserServiceImpl;
import zhth.bom.management.bom.util.change.PoiUtils;
import zhth.bom.management.bom.util.change.SpellChenge;
import zhth.bom.management.bom.util.change.WayConst;
import zhth.bom.management.bom.util.common.IMBomResponse;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


@Controller
public class MainController {

    @Autowired
    private BomMaterialItemServiceImpl bomMaterialItemService;

    @Autowired
    private BomMaterialServiceImpl bomMaterialService;

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/" ,produces = "text/plain;charset=UTF-8")
    public String login()throws  Exception{
        return "login";
    }

    @RequestMapping(value = "order-list",produces = "text/plain;charset=UTF-8")
    public String  orderlist(){
        return "order-list";
    }
    @RequestMapping(value = "/logout",produces = "text/plain;charset=UTF-8")
    public String logout(HttpSession session)throws  Exception{
        session.invalidate();
        return "login";
    }

    @RequestMapping(value = "index",produces = "text/plain;charset=UTF-8")
    public String  index(){
        return "index";
    }

    @RequestMapping(value = {"/login"},method = RequestMethod.POST)
    public  String login(String username, String password, HttpSession session){
        boolean flag=false;
        String  denglu="login";
        try{
            if(username==null&&password==null){
                return denglu;
            }else{
                flag=userService.isexist(username,password);
                if(flag){
                    User user=userService.findByUsername(username);
                    session.setAttribute("user",user);
                    session.setMaxInactiveInterval(60*60*24);
                    denglu="index";
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return denglu;
    }

    @RequestMapping(value = "welcome",produces = "text/plain;charset=UTF-8")
    public String  welcome(Model model){
        Map<String,String> map = System.getenv();
        model.addAttribute("map",map.get("COMPUTERNAME"));
        return "welcome";
    }



    @RequestMapping(value = "order-details",produces = "text/plain;charset=UTF-8")
    public  String orderdetails(){

        return  "order-details";
    }


    @RequestMapping(value="/materialuploading", method = RequestMethod.POST)
    public String materialuploading(@RequestParam("file") MultipartFile file, String username, String louhao, String explain, String remark) {
            String filepost=file.getOriginalFilename();
            String str=filepost.substring(filepost.lastIndexOf(".")+1,filepost.length());
            PoiUtils pu=null;
            try{
                pu=new PoiUtils(file.getInputStream(),str);
            }catch (Exception e){
                    e.printStackTrace();
            }
            Workbook   wb=pu.getWorkbook();
            BomMaterialItem bomMaterialItem=new BomMaterialItem();
            BuildingTable buildingTable=new BuildingTable();
            buildingTable.setBtName(louhao);
            bomMaterialItem.setBmiPerson(username);
            bomMaterialItem.setBmiExplain(explain);
            bomMaterialItem.setBmiRemark(remark);
            bomMaterialItem.setBuildingTable(buildingTable);
            IMBomResponse<BomMaterialItem> bom=bomMaterialItemService.save(bomMaterialItem);
            BomMaterialItem bomitem= bom.getData();
            Sheet sheet=wb.getSheetAt(0);
            int rows=sheet.getLastRowNum();
                    for (int i=1;i<=rows;i++){
                     Row row=sheet.getRow(i);
                     Cell c1=row.getCell(1);
                     Cell c2=row.getCell(2);
                     Cell c3=row.getCell(3);
                     try{
                         if(c3!=null){
                             bomMaterialItemService.insertbomMaterial(c1.getStringCellValue(),(int) c2.getNumericCellValue(),bomitem.getSerialNum(),c3.getStringCellValue(),bomitem.getBuildingTable().getBomMaterial());
                         }else{
                             bomMaterialItemService.insertbomMaterial(c1.getStringCellValue(),(int) c2.getNumericCellValue(),bomitem.getSerialNum(),"",bomitem.getBuildingTable().getBomMaterial());
                         }
                     }catch(Exception e){
                         e.printStackTrace();
                     }

        }
        return "order-list";
    }

    @RequestMapping(value = "order-material",produces = "text/plain;charset=UTF-8")
    public String  ordermaterial(){
        return "order-material";
    }

    @RequestMapping(value = "execldownload")
    public void execldownload(String name, String p, HttpServletResponse response, int way) throws Exception {
             SpellChenge t= new SpellChenge(name);
             String g="zhth"+t.toPingying();
        List<StockMaterialItem> bStockMaterialItemList = bomMaterialService.finbyname(g, p, way);
             System.out.println(bStockMaterialItemList.size());
        if (bStockMaterialItemList.size() == 0 && way == 0) {
            bStockMaterialItemList = bomMaterialService.matchpart(g, p, way);
        } else if (bStockMaterialItemList.size() == 0 && way == 1) {
            bStockMaterialItemList = bomMaterialService.minmaterial(g, p, way);
             }
        HSSFWorkbook wb=new HSSFWorkbook();
        HSSFSheet  sheet=wb.createSheet("材料清单");
        HSSFRow row1=sheet.createRow(0);
        HSSFCell c1=row1.createCell(0);
        HSSFCell c2=row1.createCell(1);
        HSSFCell  c3=row1.createCell(2);
        HSSFCell c4=row1.createCell(3);
        HSSFCell c5=row1.createCell(4);
        HSSFCell c6=row1.createCell(5);
        c1.setCellValue("编号");
        c2.setCellValue("材料编号");
        c3.setCellValue("材料名称");
        c4.setCellValue("材料数量");
        c5.setCellValue("材料重量");
        c6.setCellValue(name);
        for (int i=1; i<=bStockMaterialItemList.size();i++){
                  HSSFRow row2=sheet.createRow(i);
             HSSFCell cb1=row2.createCell(0);
             HSSFCell cb2=row2.createCell(1);
             HSSFCell cb3=row2.createCell(2);
             HSSFCell cb4=row2.createCell(3);
             HSSFCell cb5=row2.createCell(4);
             cb1.setCellValue(i);
             cb2.setCellValue(bStockMaterialItemList.get(i-1).getMateName());
             cb3.setCellValue(bStockMaterialItemList.get(i-1).getBmiRemark());
             cb4.setCellValue(bStockMaterialItemList.get(i-1).getSmiNum());
             cb5.setCellValue(bStockMaterialItemList.get(i-1).getQuantum().setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue());
        }
        String fname=System.currentTimeMillis()+".xls";
        response.setHeader("Content-Disposition","attachment;fileName="+fname);
        response.setContentType("application/excel");  //报头
        ServletOutputStream sos=response.getOutputStream();
        wb.write(sos);
    }
}
