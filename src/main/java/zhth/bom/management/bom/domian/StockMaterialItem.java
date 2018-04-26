package zhth.bom.management.bom.domian;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 楼栋所需材料表
 */
@Entity
public class StockMaterialItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer smiId;

    private String mateName;

    private Integer smiNum;

    private String btName;//楼号

    private Date smiTime; //操作时间

    private String bmiRemark; // 材料名称

    private BigDecimal quantum;  //总量

    @Column(nullable = true)
    private String serialNum;//序列号

    private int way;

    public int getWay() {
        return way;
    }

    public void setWay(int way) {
        this.way = way;
    }

    public StockMaterialItem() {
    }

    public Integer getSmiId() {
        return smiId;
    }

    public void setSmiId(Integer smiId) {
        this.smiId = smiId;
    }

    public String getMateName() {
        return mateName;
    }

    public void setMateName(String mateName) {
        this.mateName = mateName;
    }

    public Integer getSmiNum() {
        return smiNum;
    }

    public void setSmiNum(Integer smiNum) {
        this.smiNum = smiNum;
    }

    public String getBtName() {
        return btName;
    }

    public void setBtName(String btName) {
        this.btName = btName;
    }

    public Date getSmiTime() {
        return smiTime;
    }

    public void setSmiTime(Date smiTime) {
        this.smiTime = smiTime;
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

    public BigDecimal getQuantum() {

        return quantum;
    }

    @Column(columnDefinition = "decimal(10,4) not null")
    public void setQuantum(BigDecimal quantum) {
        this.quantum = quantum;
    }

    @Override
    public String toString() {
        return "StockMaterialItem{" +
                "smiId=" + smiId +
                ", mateName='" + mateName + '\'' +
                ", smiNum=" + smiNum +
                ", btName='" + btName + '\'' +
                ", smiTime=" + smiTime +
                ", bmiRemark='" + bmiRemark + '\'' +
                ", quantum=" + quantum +
                ", serialNum='" + serialNum + '\'' +
                ", way=" + way +
                '}';
    }
}
