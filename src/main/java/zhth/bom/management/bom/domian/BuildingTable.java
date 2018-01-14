package zhth.bom.management.bom.domian;



import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 楼号表
 *用户输入楼号
 */
@Entity
@Table(name="building_table")
@NamedStoredProcedureQueries({@NamedStoredProcedureQuery(name="Createtable",procedureName = "Createtable",parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN,name = "biaoming",type = String.class)}),
        }
)
public class BuildingTable implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @ApiModelProperty(value = "楼号主键")
    private Integer btId; //楼号主键，自动生成

    @Column(unique = true,nullable = false,length = 50)
    @ApiModelProperty(value = "楼号名称")
    private String btName; //楼号名称

    @Column(unique = true,nullable = false,length = 225)
    @ApiModelProperty(value = "楼号拼音")
    private String bomMaterial;  //楼号对应英文名称

    @ApiModelProperty(value = "楼号备注")
    private String btRemark;  //楼号备注

    @OneToMany()
    @JoinColumn(name = "building_table_bt_id")
    private Set<BomMaterialItem> bomMaterialItem=new HashSet<BomMaterialItem>();

    public Set<BomMaterialItem> getBomMaterialItem() {
        return bomMaterialItem;
    }

    public void setBomMaterialItem(Set<BomMaterialItem> bomMaterialItem) {
        this.bomMaterialItem = bomMaterialItem;
    }

    public BuildingTable() {
    }


    public Integer getBtId() {
        return btId;
    }

    public void setBtId(Integer btId) {
        this.btId = btId;
    }

    public String getBtName() {
        return btName;
    }

    public void setBtName(String btName) {
        this.btName = btName;
    }

    public String getBomMaterial() {
        return bomMaterial;
    }

    public void setBomMaterial(String bomMaterial) {
        this.bomMaterial = bomMaterial;
    }


    public String getBtRemark() {
        return btRemark;
    }

    public void setBtRemark(String btRemark) {
        this.btRemark = btRemark;
    }

    @Override
    public String toString() {
        return "BuildingTable{" +
                "btId=" + btId +
                ", btName='" + btName + '\'' +
                ", bomMaterial='" + bomMaterial + '\'' +
                ", btRemark='" + btRemark + '\'' +
                '}';
    }
}
