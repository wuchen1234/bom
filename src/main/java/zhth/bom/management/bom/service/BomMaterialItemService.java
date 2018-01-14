package zhth.bom.management.bom.service;


import zhth.bom.management.bom.domian.BomMaterialItem;
import zhth.bom.management.bom.util.common.IMBomResponse;

import java.util.List;


public interface BomMaterialItemService {

     IMBomResponse<BomMaterialItem> save(BomMaterialItem bomMaterialItem);

    void insertbomMaterial(String model, Integer num, String serialNum, String remark, String biaoming);

     List<BomMaterialItem>  findAll();

    List<BomMaterialItem>  findByBmiPerson(String name);

    List<BomMaterialItem> findByBmiExplain(String BmiExplain);

    List<BomMaterialItem> findByBmiExplainAndAndBmiPerson(String BmExplain, String bmiPerson);



}
