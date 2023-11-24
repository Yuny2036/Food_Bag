package dr.foody.food.dao;

import dr.foody.food.dto.FoodDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FoodDao {
    List<FoodDto> getList(FoodDto foodDto);
    Integer mod(FoodDto foodDto);
    Integer reg(FoodDto foodDto);
    Integer del(Integer idx);
}
