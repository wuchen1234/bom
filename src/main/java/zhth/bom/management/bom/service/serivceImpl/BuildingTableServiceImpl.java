package zhth.bom.management.bom.service.serivceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zhth.bom.management.bom.domian.BuildingTable;
import zhth.bom.management.bom.repository.BuildingTableRepository;
import zhth.bom.management.bom.service.BuildingTableService;
import zhth.bom.management.bom.util.change.ObjectUtils;
import zhth.bom.management.bom.util.change.SpellChenge;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuildingTableServiceImpl implements BuildingTableService{

    @Autowired
    private BuildingTableRepository buildingTableRepository;

    @Override
    @Transactional
    public BuildingTable save(BuildingTable buildingTable) {
           String  btname=buildingTable.getBtName();
           BuildingTable buildingTable1=buildingTableRepository.findByBtName(btname);
           //存在表就不创建表，不存在就创建
           if(ObjectUtils.isEmpty(buildingTable1)){
               SpellChenge spellChenge=new SpellChenge(btname);
               String  zhth="zhth"+spellChenge.toPingying();
               buildingTable.setBomMaterial(zhth);
               buildingTableRepository.Createtable(zhth);
               return buildingTableRepository.save(buildingTable);
           }
           return buildingTable1;
    }


}
