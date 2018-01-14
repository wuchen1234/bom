package zhth.bom.management.bom.domian;



import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * 零件表，标准零件库表
 */
@Entity
public class PartsList implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer partId; //id

    @Column(length = 20,nullable = false,unique = true)
    private String partNum;

    @Column(length = 50,nullable = false,unique = true)
    private String model;


    @Transient
    private int snum;//用来承接物料清单来的数量

    public int getSnum() {
        return snum;
    }

    public void setSnum(int snum) {
        this.snum = snum;
    }

    private String partRemark;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "part_id")
    private Set<PartsItem> partsItems=new HashSet<PartsItem>();



    public PartsList() {
    }


    public Integer getPartId() {
        return partId;
    }

    public void setPartId(Integer partId) {
        this.partId = partId;
    }

    public String getPartNum() {
        return partNum;
    }

    public void setPartNum(String partNum) {
        this.partNum = partNum;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPartRemark() {
        return partRemark;
    }

    public void setPartRemark(String partRemark) {
        this.partRemark = partRemark;
    }

    public Set<PartsItem> getPartsItems() {
        return partsItems;
    }

    public void setPartsItems(Set<PartsItem> partsItems) {
        this.partsItems = partsItems;
    }


    @Override
    public String toString() {
        return "PartsList{" +
                "partId=" + partId +
                ", partNum='" + partNum + '\'' +
                ", model='" + model + '\'' +
                ", partRemark='" + partRemark + '\'' +
                '}';
    }
}
