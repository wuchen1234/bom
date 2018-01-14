package zhth.bom.management.bom.util.change;



import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class SpellChenge {
    private String chinese;

    /**
     * @param chinese
     */
    public SpellChenge(String chinese){
        this.chinese=chinese;
    }

    /**
     * 汉字转为拼音
     * @return
     */

    public  String toPingying(){
        String pingying="";
        char [] tochinese=chinese.trim().toCharArray();
        HanyuPinyinOutputFormat hanyuPinyinOutputFormat=new HanyuPinyinOutputFormat();
        hanyuPinyinOutputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        hanyuPinyinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
       hanyuPinyinOutputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
        try {
            for (int i = 0; i <tochinese.length; i++) {
                if(Character.toString(tochinese[i]).matches("[\\u4E00-\\u9FA5]+")){
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(tochinese[i], hanyuPinyinOutputFormat);
                    pingying += temp[0].toString();
                }else if(!Character.toString(tochinese[i]).equals(" ")) {
                    pingying+=Character.toString(tochinese[i]);
                }
            }
        }catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        return pingying;
    }

}
