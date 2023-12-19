package dr.foody.foodPresetSalad.ctrl;

import dr.foody.foodPresetSalad.dto.FoodPresetSaladDto;
import dr.foody.foodPresetSalad.svc.FoodPresetSaladSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/foodPresetSalad")
public class FoodPresetSaladCtrl {
    @Autowired
    FoodPresetSaladSvc foodPresetSaladService;

    @GetMapping("/getList")
    public Object getInsDevList(@ModelAttribute FoodPresetSaladDto foodPresetSaladDto) {
        return foodPresetSaladService.getList(foodPresetSaladDto);
    }

    @PostMapping("/regist")
    public Object regInsDev(@ModelAttribute FoodPresetSaladDto foodPresetSaladDto) {
        return foodPresetSaladService.regist(foodPresetSaladDto);
    }

    @DeleteMapping("/delete")
    public Object delInsDev(@RequestParam(value="idx",required=true) Integer idx) {
        return foodPresetSaladService.delete(idx);
    }

    @PutMapping("/update")
    public Object modInsDev( @ModelAttribute FoodPresetSaladDto foodPresetSaladDto) {
        return foodPresetSaladService.modify(foodPresetSaladDto);
    }
}
