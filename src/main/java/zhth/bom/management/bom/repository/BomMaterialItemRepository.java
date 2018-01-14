package zhth.bom.management.bom.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import zhth.bom.management.bom.domian.BomMaterialItem;


import java.util.List;

public interface BomMaterialItemRepository extends JpaRepository<BomMaterialItem,Integer> {

    @Procedure(name ="insertCreatetable")
     void insertbomMaterial(@Param("model") String model, @Param("num") Integer num, @Param("serialNum") String serialNum, @Param("remark") String remark, @Param("biaoming") String biaoming);

    @Query("select p from BomMaterialItem p where p.bmiPerson like %?1%")
    List<BomMaterialItem>  findByBmiPerson(String name);

    @Query("select p from BomMaterialItem p where p.bmiExplain like %?1%")
    List<BomMaterialItem> findByBmiExplain(String BmiExplain);

    @Query(value = "select p from BomMaterialItem p where (p.bmiExplain like %?1%)  and (p.bmiPerson like %?2%)")
    List<BomMaterialItem> findByBmiExplainAndAndBmiPerson(String BmExplain, String bmiPerson);
}
