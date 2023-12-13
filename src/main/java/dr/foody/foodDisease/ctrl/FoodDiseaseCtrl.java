package dr.foody.foodDisease.ctrl;

import dr.foody.foodDisease.dto.FoodDiseaseDto;
import dr.foody.foodDisease.svc.FoodDiseaseSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/foodDisease")
public class FoodDiseaseCtrl {
    @Autowired
    FoodDiseaseSvc foodDiseaseSerivce;

    @GetMapping("/getList")
    public Object getInsDevList(@ModelAttribute FoodDiseaseDto foodDiseaseDto) {
        return foodDiseaseSerivce.getList(foodDiseaseDto);
    }

    @PostMapping("/regist")
    public Object regInsDev(@ModelAttribute FoodDiseaseDto foodDiseaseDto) {
        return foodDiseaseSerivce.regist(foodDiseaseDto);
    }

    @DeleteMapping("/delete")
    public Object delInsDev(@RequestParam(value="idx",required=true) Integer idx) {
        return foodDiseaseSerivce.delete(idx);
    }

    @PutMapping("/update")
    public Object modInsDev( @ModelAttribute FoodDiseaseDto foodDiseaseDto) {
        return foodDiseaseSerivce.modify(foodDiseaseDto);
    }
}
