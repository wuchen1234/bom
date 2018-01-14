package zhth.bom.management.bom.util.change;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;

public class PoiUtils {

    private Workbook workbook;


    public  PoiUtils(InputStream in,String postfix)
    {
        try{
            if(postfix.equals("xls")){
                this.workbook=new HSSFWorkbook(in);
            }else if(postfix.equals("xlsx")){
                this.workbook = new XSSFWorkbook(in);
            }
            }catch (Exception ex){
                    ex.printStackTrace();
        }
    }

     public Workbook getWorkbook() {
             return workbook;
     }
}
