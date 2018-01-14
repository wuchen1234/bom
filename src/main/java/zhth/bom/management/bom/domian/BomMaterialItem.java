package zhth.bom.management.bom.domian;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 操作人员信息
 */
@Entity
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name="insertCreatetable",procedureName = "insertCreatetable",parameters= {
                @StoredProcedureParameter(mode = ParameterMode.IN,name = "model",type =String.class ),
                @StoredProcedureParameter(mode =ParameterMode.IN ,name = "num",type = Integer.class),
                @StoredProcedureParameter(mode =ParameterMode.IN ,name = "serialNum",type = String.class),
                @StoredProcedureParameter(mode =ParameterMode.IN ,name = "remark",type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN,name = "biaoming",type = String.class)
        })
})
public class BomMaterialItem implements Serializable{
    private static final long serialVersionUID = -906357110051689484L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bmiList;

    @Column(length = 50,nullable = true )
    private  String  bmiPerson;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(nullable = true)
    private Date  bmiTime;

    @Column(nullable = true)
    private String serialNum;//序列号


    private String bmiExplain;//物料说明

    private String bmiRemark;

    @ManyToOne
    private BuildingTable buildingTable;




    public Integer getBmiList() {
        return bmiList;
    }

    public void setBmiList(Integer bmiList) {
        this.bmiList = bmiList;
    }

    public String getBmiPerson() {
        return bmiPerson;
    }

    public void setBmiPerson(String bmiPerson) {
        this.bmiPerson = bmiPerson;
    }

    public Date getBmiTime() {
        return bmiTime;
    }

    public void setBmiTime(Date bmiTime) {
        this.bmiTime = bmiTime;
    }

    public String getBmiExplain() {
        return bmiExplain;
    }
    public String getBmiRemark() {
        return bmiRemark;
    }

    public void setBmiRemark(String bmiRemark) {
        this.bmiRemark = bmiRemark;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public void setBmiExplain(String bmiExplain) {
        this.bmiExplain = bmiExplain;
    }

    public BuildingTable getBuildingTable() {
        return buildingTable;
    }

    public void setBuildingTable(BuildingTable buildingTable) {
        this.buildingTable = buildingTable;
    }



    @Override
    public String toString() {
        return "BomMaterialItem{" +
                "bmiList=" + bmiList +
                ", bmiPerson='" + bmiPerson + '\'' +
                ", bmiTime=" + bmiTime +
                ", serialNum='" + serialNum + '\'' +
                ", bmiExplain='" + bmiExplain + '\'' +
                ", bmiRemark='" + bmiRemark + '\'' +
                '}';
    }
}
