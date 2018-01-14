package zhth.bom.management.bom.domianextend;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class BomMaterialItemExtend {

    private String person;//人

    private String explain;//楼号

    private String time;//时间

    private String serial;  //序列号

    private String towernum;//楼编号

    private String remark; //备注

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getTowernum() {
        return towernum;
    }

    public void setTowernum(String towernum) {
        this.towernum = towernum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "BomMaterialItemExtend{" +
                "person='" + person + '\'' +
                ", explain='" + explain + '\'' +
                ", time=" + time +
                ", serial='" + serial + '\'' +
                ", towernum='" + towernum + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
