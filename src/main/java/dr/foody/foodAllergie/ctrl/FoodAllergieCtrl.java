package dr.foody.foodAllergie.ctrl;

import dr.foody.foodAllergie.dto.FoodAllergieDto;
import dr.foody.foodAllergie.svc.FoodAllergieSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/foodAllergie")
public class FoodAllergieCtrl {
    @Autowired
    FoodAllergieSvc foodAllergieService;

    @GetMapping("/getList")
    public Object getInsDevList(@ModelAttribute FoodAllergieDto foodAllergieDto) {
        return foodAllergieService.getList(foodAllergieDto);
    }

    @PostMapping("/regist")
    public Object regInsDev(@ModelAttribute FoodAllergieDto foodAllergieDto) {
        return foodAllergieService.regist(foodAllergieDto);
    }

    @DeleteMapping("/delete")
    public Object delInsDev(@RequestParam(value="idx",required=true) Integer idx) {
        return foodAllergieService.delete(idx);
    }

    @PutMapping("/update")
    public Object modInsDev( @ModelAttribute FoodAllergieDto foodAllergieDto) {
        return foodAllergieService.modify(foodAllergieDto);
    }
}
