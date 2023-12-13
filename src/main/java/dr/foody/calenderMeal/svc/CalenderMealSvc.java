package dr.foody.calenderMeal.svc;

import dr.foody.calenderMeal.dao.CalenderMealDao;
import dr.foody.calenderMeal.dto.CalenderMealDto;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CalenderMealSvc {
    @Autowired
    CalenderMealDao calenderMealDao;

    public Object getList(CalenderMealDto calenderMealDto){
        return calenderMealDao.getList(calenderMealDto);
    }
    public Object modify(CalenderMealDto calenderMealDto){
        return calenderMealDao.mod(calenderMealDto);
    }
    public Object regist(CalenderMealDto calenderMealDto){
        return calenderMealDao.reg(calenderMealDto);
    }
    public Object delete(Integer idx){
        return calenderMealDao.del(idx);
    }
}
