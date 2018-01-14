package zhth.bom.management.bom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import zhth.bom.management.bom.domian.PartsList;

public interface PartsListRepository extends JpaRepository<PartsList,Integer> {

        @Query("select p from PartsList p where model= ?1")
         PartsList findByModel(String model);
}
