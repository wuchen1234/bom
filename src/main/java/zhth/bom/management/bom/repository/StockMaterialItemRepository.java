package zhth.bom.management.bom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import zhth.bom.management.bom.domian.StockMaterialItem;

import java.util.List;


public interface StockMaterialItemRepository extends JpaRepository<StockMaterialItem,Integer> {

    /**
     * 查询楼栋材料进货清单
     * @param name
     * @param p
     * @return
     */
    @Query("select p from StockMaterialItem p where btName=?1 and serialNum=?2")
    List<StockMaterialItem>   findbyname(String name, String p);
}
