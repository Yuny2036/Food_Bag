package dr.foody.foodNutri.ctrl;

import dr.foody.foodNutri.dto.FoodNutriDto;
import dr.foody.foodNutri.svc.FoodNutriSvc;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

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

    @GetMapping("/getNutrition")
    public Object getNutrition(@ModelAttribute FoodNutriDto foodNutriDto){
        HashMap<String, Object> response = foodNutriService.getNutritionMap(foodNutriDto);
        JSONObject obj = new JSONObject();

        obj.put("rst_cd", response.get("rst_cd"));
        obj.put("rst_desc", response.get("rst_desc"));
        obj.put("kcal", response.get("kcal"));
        obj.put("carbo", response.get("carbo"));
        obj.put("protein", response.get("protein"));
        obj.put("fat", response.get("fat"));
        obj.put("salt", response.get("salt"));
        obj.put("chole", response.get("chole"));

        return obj.toString();

        /*
        API : /foodnutri/getNutrition
        호출방식 : GET
        Input : foodIdx(음식 키idx 값)
        Output : "rst_cd" > 200: 성공
                            -1 : 실패, 음식 키가 유효하지 않음 (없는 음식)
                 "rst_desc" : 각 rst_cd에 따른 오류 설명
                 "kcal" : 칼로리
                 "carbo" : 탄수화물
                 "protein" : 단백질
                 "fat" : 지방
                 "salt" : 염분
                 "chole" : 콜레스테롤
         */
    }
}