package zhth.bom.management.bom.domian;

/**
 * 物料清单类
 */
public class BomMaterial {

    private Integer bmId;//物料id

    private String bmModel;//物料型号

    private Integer bmNum;//物料数量

    private String serialNum;//物料编号

    private String bmRemark;//非标件替换

    public Integer getBmId() {
        return bmId;
    }

    public void setBmId(Integer bmId) {
        this.bmId = bmId;
    }

    public String getBmModel() {
        return bmModel;
    }

    public void setBmModel(String bmModel) {
        this.bmModel = bmModel;
    }

    public Integer getBmNum() {
        return bmNum;
    }

    public void setBmNum(Integer bmNum) {
        this.bmNum = bmNum;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public String getBmRemark() {
        return bmRemark;
    }

    public void setBmRemark(String bmRemark) {
        this.bmRemark = bmRemark;
    }

    @Override
    public String toString() {
        return "BomMaterial{" +
                "bmId=" + bmId +
                ", bmModel='" + bmModel + '\'' +
                ", bmNum=" + bmNum +
                ", serialNum='" + serialNum + '\'' +
                ", bmRemark='" + bmRemark + '\'' +
                '}';
    }
}
