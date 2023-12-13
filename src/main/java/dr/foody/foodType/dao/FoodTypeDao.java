package dr.foody.foodType.dao;

import dr.foody.foodType.dto.FoodTypeDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FoodTypeDao {
    List<FoodTypeDto> getList(FoodTypeDto foodTypeDto);
    Integer mod(FoodTypeDto foodTypeDto);
    Integer reg(FoodTypeDto foodTypeDto);
    Integer del(Integer idx);
}
