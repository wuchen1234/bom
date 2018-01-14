package zhth.bom.management.bom.util.change;

import zhth.bom.management.bom.domianextend.ItemTemporary;
import zhth.bom.management.bom.util.exception.MyException;

import java.util.ArrayList;
import java.util.List;

public class ContainUtil {

    public List<ItemTemporary> contain(List<ItemTemporary> o){
        List<ItemTemporary>  list=new ArrayList<ItemTemporary>();
        if (o==null) {
                throw new MyException("空指针");
        } else {
            for (ItemTemporary g: o) {
                for (ItemTemporary t: list) {
                    if(!(g.getBigDecimal().equals(t.getBigDecimal())&&g.getMaterialList().getMateName().equals(t.getMaterialList().getMateName())))
                                list.add(g);
                }
            }
        }
        return list;
    }
}
