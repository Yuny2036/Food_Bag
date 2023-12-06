package dr.foody.foodNutri.dao;

import dr.foody.foodNutri.dto.FoodNutriDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FoodNutriDao {
    List<FoodNutriDto> getList(FoodNutriDto foodNutriDto);
    Integer mod(FoodNutriDto foodNutriDto);
    Integer reg(FoodNutriDto foodNutriDto);
    Integer del(Integer idx);
}
