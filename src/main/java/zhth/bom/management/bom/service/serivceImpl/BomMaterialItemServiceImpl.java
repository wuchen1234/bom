package zhth.bom.management.bom.service.serivceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zhth.bom.management.bom.domian.BomMaterialItem;
import zhth.bom.management.bom.domian.BuildingTable;
import zhth.bom.management.bom.repository.BomMaterialItemRepository;
import zhth.bom.management.bom.service.BomMaterialItemService;
import zhth.bom.management.bom.util.change.Serial;
import zhth.bom.management.bom.util.common.IMBomResponse;

import java.util.Date;
import java.util.List;

@Service
public class BomMaterialItemServiceImpl implements BomMaterialItemService{

    private BuildingTableServiceImpl buildingTableService;

    private BomMaterialItemRepository bomMaterialItemRepository;

    @Autowired
    public BomMaterialItemServiceImpl(BuildingTableServiceImpl buildingTableService, BomMaterialItemRepository bomMaterialItemRepository) {
        this.buildingTableService = buildingTableService;
        this.bomMaterialItemRepository = bomMaterialItemRepository;
    }

    @Override
    @Transactional
    public IMBomResponse<BomMaterialItem> save(BomMaterialItem bomMaterialItem) {
         BuildingTable buildingTable=bomMaterialItem.getBuildingTable();
         BuildingTable buildingTable1=buildingTableService.save(buildingTable);
         bomMaterialItem.setBmiTime(new Date());
         bomMaterialItem.setSerialNum(Serial.serialNumber());
         bomMaterialItem.setBuildingTable(buildingTable1);
         BomMaterialItem bomMaterial=bomMaterialItemRepository.save(bomMaterialItem);
        return IMBomResponse.build(bomMaterial);
    }

    @Override
    public void insertbomMaterial(String model, Integer num, String serialNum, String remark, String biaoming) {
        bomMaterialItemRepository.insertbomMaterial(model,num,serialNum,remark,biaoming);
    }

    @Override
    public List<BomMaterialItem> findAll() {
        return bomMaterialItemRepository.findAll();
    }

    @Override
    public List<BomMaterialItem> findByBmiPerson(String name) {
        return bomMaterialItemRepository.findByBmiPerson(name);
    }

    @Override
    public List<BomMaterialItem> findByBmiExplain(String BmiExplain) {
        return bomMaterialItemRepository.findByBmiExplain(BmiExplain);
    }

    @Override
    public List<BomMaterialItem> findByBmiExplainAndAndBmiPerson(String BmExplain, String bmiPerson) {
        return bomMaterialItemRepository.findByBmiExplainAndAndBmiPerson(BmExplain,bmiPerson);
    }
}
