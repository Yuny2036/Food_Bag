package dr.foody.foodIngredient.ctrl;

import dr.foody.foodIngredient.dto.FoodIngredientDto;
import dr.foody.foodIngredient.svc.FoodIngredientSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/foodIngredient")
public class FoodIngredientCtrl {
    @Autowired
    FoodIngredientSvc foodIngredientSerivce;

    @GetMapping("/getList")
    public Object getInsDevList(@ModelAttribute FoodIngredientDto foodIngredientDto) {
        return foodIngredientSerivce.getList(foodIngredientDto);
    }

    @PostMapping("/regist")
    public Object regInsDev(@ModelAttribute FoodIngredientDto foodIngredientDto) {
        return foodIngredientSerivce.regist(foodIngredientDto);
    }

    @DeleteMapping("/delete")
    public Object delInsDev(@RequestParam(value="idx",required=true) Integer idx) {
        return foodIngredientSerivce.delete(idx);
    }

    @PutMapping("/update")
    public Object modInsDev( @ModelAttribute FoodIngredientDto foodIngredientDto) {
        return foodIngredientSerivce.modify(foodIngredientDto);
    }
}
