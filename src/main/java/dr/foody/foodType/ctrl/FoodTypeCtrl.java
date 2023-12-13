package dr.foody.foodType.ctrl;

import dr.foody.foodType.dto.FoodTypeDto;
import dr.foody.foodType.svc.FoodTypeSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/foodType")
public class FoodTypeCtrl {
    @Autowired
    FoodTypeSvc foodTypeService;

    @GetMapping("/getList")
    public Object getInsDevList(@ModelAttribute FoodTypeDto foodTypeDto) {
        return foodTypeService.getList(foodTypeDto);
    }

    @PostMapping("/regist")
    public Object regInsDev(@ModelAttribute FoodTypeDto foodTypeDto) {
        return foodTypeService.regist(foodTypeDto);
    }

    @DeleteMapping("/delete")
    public Object delInsDev(@RequestParam(value="idx",required=true) Integer idx) {
        return foodTypeService.delete(idx);
    }

    @PutMapping("/update")
    public Object modInsDev( @ModelAttribute FoodTypeDto foodTypeDto) {
        return foodTypeService.modify(foodTypeDto);
    }
}
