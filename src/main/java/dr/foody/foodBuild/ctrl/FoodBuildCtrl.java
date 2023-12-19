package dr.foody.foodBuild.ctrl;

import dr.foody.foodBuild.svc.MealBuildSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/foodBuild")
public class FoodBuildCtrl {
    @Autowired
    MealBuildSvc mealBuildService;

    @GetMapping("/getWeeklyMeals")
    public Object regInsDev(@RequestParam(value="idx",required=true) Integer userIdx ) {
        return mealBuildService.setFood(userIdx);
    }
}
