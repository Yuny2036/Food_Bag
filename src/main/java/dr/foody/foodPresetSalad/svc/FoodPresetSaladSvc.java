package dr.foody.foodPresetSalad.svc;

import dr.foody.foodPresetSalad.dao.FoodPresetSaladDao;
import dr.foody.foodPresetSalad.dto.FoodPresetSaladDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FoodPresetSaladSvc {
    @Autowired
    FoodPresetSaladDao foodPresetSaladDao;

    public Object getList(FoodPresetSaladDto foodPresetSaladDto){
        return foodPresetSaladDao.getList(foodPresetSaladDto);
    }

    public Object modify(FoodPresetSaladDto foodPresetSaladDto){
        return foodPresetSaladDao.mod(foodPresetSaladDto);
    }
    public Object regist(FoodPresetSaladDto foodPresetSaladDto){
        return foodPresetSaladDao.reg(foodPresetSaladDto);
    }
    public Object delete(Integer idx){
        return foodPresetSaladDao.del(idx);
    }
}
