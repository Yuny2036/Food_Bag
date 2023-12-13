package dr.foody.food.svc;

import dr.foody.food.dao.FoodDao;
import dr.foody.food.dto.FoodDto;
import dr.foody.food.dto.FoodNutritionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
public class FoodSvc {
    @Autowired
    FoodDao foodDao;

    public Object getList(FoodDto foodDto){
        return foodDao.getList(foodDto);
    }

    public Object modify(FoodDto foodDto){
        return foodDao.mod(foodDto);
    }
    public Object regist(FoodDto foodDto){
        return foodDao.reg(foodDto);
    }
    public Object delete(Integer idx){
        return foodDao.del(idx);
    }
}
