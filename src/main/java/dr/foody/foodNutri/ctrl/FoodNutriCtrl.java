package dr.foody.foodNutri.ctrl;

import dr.foody.foodNutri.dto.FoodNutriDto;
import dr.foody.foodNutri.svc.FoodNutriSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/foodnutri")
public class FoodNutriCtrl {
    @Autowired
    FoodNutriSvc foodNutriService;

    @GetMapping("/getList")
    public Object getInsDevList(@ModelAttribute FoodNutriDto foodNutriDto) {
        return foodNutriService.getList(foodNutriDto);
    }

    @PostMapping("/regist")
    public Object regInsDev(@ModelAttribute FoodNutriDto foodNutriDto) {
        return foodNutriService.regist(foodNutriDto);
    }

    @DeleteMapping("/delete")
    public Object delInsDev(@RequestParam(value="idx",required=true) Integer idx) {
        return foodNutriService.delete(idx);
    }

    @PutMapping("/update")
    public Object modInsDev( @ModelAttribute FoodNutriDto foodNutriDto) {
        return foodNutriService.modify(foodNutriDto);
    }
}