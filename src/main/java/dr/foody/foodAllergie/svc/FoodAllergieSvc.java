package dr.foody.foodAllergie.svc;

import dr.foody.foodAllergie.dao.FoodAllergieDao;
import dr.foody.foodAllergie.dto.FoodAllergieDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FoodAllergieSvc {
    @Autowired
    FoodAllergieDao foodAllergieDao;

    public Object getList(FoodAllergieDto foodAllergieDto){
        return foodAllergieDao.getList(foodAllergieDto);
    }

    public Object modify(FoodAllergieDto foodAllergieDto){
        return foodAllergieDao.mod(foodAllergieDto);
    }
    public Object regist(FoodAllergieDto foodAllergieDto){
        return foodAllergieDao.reg(foodAllergieDto);
    }
    public Object delete(Integer idx){
        return foodAllergieDao.del(idx);
    }
}
