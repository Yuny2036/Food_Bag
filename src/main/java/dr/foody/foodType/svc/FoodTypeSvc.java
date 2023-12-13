package dr.foody.foodType.svc;

import dr.foody.foodType.dao.FoodTypeDao;
import dr.foody.foodType.dto.FoodTypeDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FoodTypeSvc {
    @Autowired
    FoodTypeDao foodTypeDao;

    public Object getList(FoodTypeDto foodTypeDto){
        return foodTypeDao.getList(foodTypeDto);
    }

    public Object modify(FoodTypeDto foodTypeDto){
        return foodTypeDao.mod(foodTypeDto);
    }
    public Object regist(FoodTypeDto foodTypeDto){
        return foodTypeDao.reg(foodTypeDto);
    }
    public Object delete(Integer idx){
        return foodTypeDao.del(idx);
    }
}
