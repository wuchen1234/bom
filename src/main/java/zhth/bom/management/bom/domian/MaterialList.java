package zhth.bom.management.bom.domian;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * 材料表
 */
@Entity
public class MaterialList implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mateId;

    private String mateCode;//代码编号

    private String mateName;//材料名称

    @Column(columnDefinition = "char(4)  DEFAULT 'N' COMMENT '材料类别'")
    private String materialCategories;//材料类别

    @Column(columnDefinition = "decimal(5,3) not null")
    private BigDecimal lineDensity;//材料线密度

    @Column(columnDefinition = "decimal(5,3) not null")
    private BigDecimal sawHead; //锯头

    @Column(columnDefinition = "decimal(5,3) not null")
    private BigDecimal sawKerf;  //锯缝

    @Column(columnDefinition = "decimal(5,3) not null")
    private BigDecimal mateLength;

    private String mateRemrk;

    @OneToMany
    @JoinColumn(name = "mate_id")
    private Set<PartsItem> partsItems=new HashSet<PartsItem>();


    public MaterialList() {
    }


    public Integer getMateId() {
        return mateId;
    }

    public void setMateId(Integer mateId) {
        this.mateId = mateId;
    }

    public String getMateCode() {
        return mateCode;
    }

    public void setMateCode(String mateCode) {
        this.mateCode = mateCode;
    }

    public String getMateName() {
        return mateName;
    }

    public void setMateName(String mateName) {
        this.mateName = mateName;
    }

    public String getMaterialCategories() {
        return materialCategories;
    }

    public void setMaterialCategories(String materialCategories) {
        this.materialCategories = materialCategories;
    }

    public BigDecimal getLineDensity() {
        return lineDensity;
    }

    public void setLineDensity(BigDecimal lineDensity) {
        this.lineDensity = lineDensity;
    }

    public BigDecimal getSawHead() {
        return sawHead;
    }

    public void setSawHead(BigDecimal sawHead) {
        this.sawHead = sawHead;
    }

    public BigDecimal getSawKerf() {
        return sawKerf;
    }

    public void setSawKerf(BigDecimal sawKerf) {
        this.sawKerf = sawKerf;
    }

    public BigDecimal getMateLength() {
        return mateLength;
    }

    public void setMateLength(BigDecimal mateLength) {
        this.mateLength = mateLength;
    }

    public String getMateRemrk() {
        return mateRemrk;
    }

    public void setMateRemrk(String mateRemrk) {
        this.mateRemrk = mateRemrk;
    }

    public Set<PartsItem> getPartsItems() {
        return partsItems;
    }

    public void setPartsItems(Set<PartsItem> partsItems) {
        this.partsItems = partsItems;
    }

    @Override
    public String toString() {
        return "MaterialList{" +
                "mateId=" + mateId +
                ", mateCode='" + mateCode + '\'' +
                ", mateName='" + mateName + '\'' +
                ", materialCategories='" + materialCategories + '\'' +
                ", lineDensity=" + lineDensity +
                ", sawHead=" + sawHead +
                ", sawKerf=" + sawKerf +
                ", mateLength=" + mateLength +
                ", mateRemrk='" + mateRemrk + '\'' +
                '}';
    }

}
