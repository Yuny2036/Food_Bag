package dr.foody.foodNutri.svc;

import dr.foody.foodNutri.dao.FoodNutriDao;
import dr.foody.foodNutri.dto.FoodNutriDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FoodNutriSvc {
    @Autowired
    FoodNutriDao foodNutriDao;

    public Object getList(FoodNutriDto foodNutriDto){
        return foodNutriDao.getList(foodNutriDto);
    }

    public Object modify(FoodNutriDto foodNutriDto){
        return foodNutriDao.mod(foodNutriDto);
    }
    public Object regist(FoodNutriDto foodNutriDto){
        return foodNutriDao.reg(foodNutriDto);
    }
    public Object delete(Integer idx){
        return foodNutriDao.del(idx);
    }
}
