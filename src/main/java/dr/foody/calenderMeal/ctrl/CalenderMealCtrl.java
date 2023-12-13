package dr.foody.calenderMeal.ctrl;

import dr.foody.calenderMeal.dto.CalenderMealDto;
import dr.foody.calenderMeal.svc.CalenderMealSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calenderMeal")
public class CalenderMealCtrl {
    @Autowired
    CalenderMealSvc calenderMealService;

    @GetMapping("/getList")
    public Object getInsDevList(@ModelAttribute CalenderMealDto calenderMealDto) {
        return calenderMealService.getList(calenderMealDto);
    }

    @PostMapping("/regist")
    public Object regInsDev(@ModelAttribute CalenderMealDto calenderMealDto) {
        return calenderMealService.regist(calenderMealDto);
    }

    @DeleteMapping("/delete")
    public Object delInsDev(@RequestParam(value="idx",required=true) Integer idx) {
        return calenderMealService.delete(idx);
    }

    @PutMapping("/update")
    public Object modInsDev( @ModelAttribute CalenderMealDto calenderMealDto) {
        return calenderMealService.modify(calenderMealDto);
    }
}
