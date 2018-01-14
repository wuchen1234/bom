package zhth.bom.management.bom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zhth.bom.management.bom.domian.PartsItem;

public interface PartsItemRepository extends JpaRepository<PartsItem,Integer> {
}
