package zhth.bom.management.bom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import zhth.bom.management.bom.domian.BuildingTable;

import java.util.List;

public interface BuildingTableRepository extends JpaRepository<BuildingTable,Integer>{

     BuildingTable findByBtName(String name);

    @Procedure(name = "Createtable")
     void Createtable(@Param("biaoming") String biaoming);


    @Query("select p from BuildingTable p where p.bomMaterial like %?1%")
    List<BuildingTable>  findBybt(String name);
}
