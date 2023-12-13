package dr.foody.foodAllergie.dao;

import dr.foody.foodAllergie.dto.FoodAllergieDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FoodAllergieDao {
    List<FoodAllergieDto> getList(FoodAllergieDto foodAllergieDto);
    Integer mod(FoodAllergieDto foodAllergieDto);
    Integer reg(FoodAllergieDto foodAllergieDto);
    Integer del(Integer idx);
}
