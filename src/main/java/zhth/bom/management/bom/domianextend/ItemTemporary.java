package zhth.bom.management.bom.domianextend;

import zhth.bom.management.bom.domian.MaterialList;

import java.math.BigDecimal;

public class ItemTemporary {

    private MaterialList materialList; // 材料的名称

    private BigDecimal bigDecimal;  //材料的长度

    private Integer sum;   // 数量

    private  BigDecimal remain; //剩余

    public MaterialList getMaterialList() {
        return materialList;
    }

    public void setMaterialList(MaterialList materialList) {
        this.materialList = materialList;
    }

    public BigDecimal getBigDecimal() {
        return bigDecimal;
    }

    public void setBigDecimal(BigDecimal bigDecimal) {
        this.bigDecimal = bigDecimal;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public BigDecimal getRemain() {
        return remain;
    }

    public void setRemain(BigDecimal remain) {
        this.remain = remain;
    }

    @Override
    public boolean equals(Object obj) {
        ItemTemporary it=(ItemTemporary)obj;
        return this.bigDecimal==it.getBigDecimal()&&this.getMaterialList().getMateCode().equals(it.getMaterialList().getMateCode());
    }
}
