package zhth.bom.management.bom.util.change;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Serial {
    /**
     * 生成是个序列号
     * @return
     */
    public static String serialNumber(){
        String  str="L";
        Date log=new Date();
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd/HH:mm");
         str=str+formatter.format(log);
        return str;
    }
    public  static  String serialTime(Date log){
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return  formatter.format(log);
    }
}
