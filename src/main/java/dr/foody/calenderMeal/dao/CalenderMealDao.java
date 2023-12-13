package dr.foody.calenderMeal.dao;

import dr.foody.calenderMeal.dto.CalenderMealDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CalenderMealDao {
    List<CalenderMealDto> getList(CalenderMealDto calenderMealDto);
    Integer mod(CalenderMealDto calenderMealDto);
    Integer reg(CalenderMealDto calenderMealDto);
    Integer del(Integer idx);
}
