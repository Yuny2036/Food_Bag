package dr.foody.foodIngredient.svc;

import dr.foody.foodIngredient.dao.FoodIngredientDao;
import dr.foody.foodIngredient.dto.FoodIngredientDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FoodIngredientSvc {
    @Autowired
    FoodIngredientDao foodIngredientDao;

    public Object getList(FoodIngredientDto foodIngredientDto){
        return foodIngredientDao.getList(foodIngredientDto);
    }

    public Object modify(FoodIngredientDto foodIngredientDto){
        return foodIngredientDao.mod(foodIngredientDto);
    }
    public Object regist(FoodIngredientDto foodIngredientDto){
        return foodIngredientDao.reg(foodIngredientDto);
    }
    public Object delete(Integer idx){
        return foodIngredientDao.del(idx);
    }
}
