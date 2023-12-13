package dr.foody.foodIngredient.dao;

import dr.foody.foodIngredient.dto.FoodIngredientDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FoodIngredientDao {
    List<FoodIngredientDto> getList(FoodIngredientDto foodIngredientDto);
    Integer mod(FoodIngredientDto foodIngredientDto);
    Integer reg(FoodIngredientDto foodIngredientDto);
    Integer del(Integer idx);
}
