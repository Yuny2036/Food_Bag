package dr.foody.foodDisease.svc;

import dr.foody.foodDisease.dao.FoodDiseaseDao;
import dr.foody.foodDisease.dto.FoodDiseaseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FoodDiseaseSvc {
    @Autowired
    FoodDiseaseDao foodDiseaseDao;

    public Object getList(FoodDiseaseDto foodDiseaseDto){
        return foodDiseaseDao.getList(foodDiseaseDto);
    }

    public Object modify(FoodDiseaseDto foodDiseaseDto){
        return foodDiseaseDao.mod(foodDiseaseDto);
    }
    public Object regist(FoodDiseaseDto foodDiseaseDto){
        return foodDiseaseDao.reg(foodDiseaseDto);
    }
    public Object delete(Integer idx){
        return foodDiseaseDao.del(idx);
    }
}
