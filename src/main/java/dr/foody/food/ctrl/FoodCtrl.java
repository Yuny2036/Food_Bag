package dr.foody.food.ctrl;

import dr.foody.food.dto.FoodDto;
import dr.foody.food.svc.FoodSvc;
import dr.foody.food.dto.FoodNutritionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/food")
public class FoodCtrl {

    @Autowired
    FoodSvc foodService;

    @GetMapping("/getList")
    public Object getInsDevList(@ModelAttribute FoodDto foodDto) {
        return foodService.getList(foodDto);
    }

    @PostMapping("/regist")
    public Object regInsDev(@ModelAttribute FoodDto foodDto) {
        return foodService.regist(foodDto);
    }

    @DeleteMapping("/delete")
    public Object delInsDev(@RequestParam(value="idx",required=true) Integer idx) {
        return foodService.delete(idx);
    }

    @PutMapping("/update")
    public Object modInsDev( @ModelAttribute FoodDto foodDto) {
        return foodService.modify(foodDto);
    }
}
