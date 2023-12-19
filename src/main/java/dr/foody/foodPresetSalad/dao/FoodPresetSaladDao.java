package dr.foody.foodPresetSalad.dao;


import dr.foody.foodPresetSalad.dto.FoodPresetSaladDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FoodPresetSaladDao {
    List<FoodPresetSaladDto> getList(FoodPresetSaladDto foodPresetSaladDto);
    Integer mod(FoodPresetSaladDto foodPresetSaladDto);
    Integer reg(FoodPresetSaladDto foodPresetSaladDto);
    Integer del(Integer idx);
}
