package zhth.bom.management.bom.domian;



import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 零件用料明细表
 */
@Entity
public class PartsItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer piId;

    @ManyToOne
    @JoinColumn(name = "part_id")
    private PartsList partsLists;

    @Column(columnDefinition = "decimal(5,3) not null")
    private BigDecimal piLength;

    private Integer piNum;

    private String piRemark;


   @JoinColumn(name = "mate_id")
   @ManyToOne
    private MaterialList meterialList;





    public PartsItem() {
    }

    public Integer getPiId() {
        return piId;
    }

    public void setPiId(Integer piId) {
        this.piId = piId;
    }

    public PartsList getPartsLists() {
        return partsLists;
    }

    public void setPartsLists(PartsList partsLists) {
        this.partsLists = partsLists;
    }


    public BigDecimal getPiLength() {
        return piLength;
    }

    public void setPiLength(BigDecimal piLength) {
        this.piLength = piLength;
    }

    public Integer getPiNum() {
        return piNum;
    }

    public void setPiNum(Integer piNum) {
        this.piNum = piNum;
    }

    public String getPiRemark() {
        return piRemark;
    }

    public void setPiRemark(String piRemark) {
        this.piRemark = piRemark;
    }

    public MaterialList getMeterialList() {
        return meterialList;
    }

    public void setMeterialList(MaterialList meterialList) {
        this.meterialList = meterialList;
    }

    @Override
    public String toString() {
        return "PartsItem{" +
                "piId=" + piId +
                ", piLength=" + piLength +
                ", piNum=" + piNum +
                ", piRemark='" + piRemark + '\'' +
                '}';
    }
}
