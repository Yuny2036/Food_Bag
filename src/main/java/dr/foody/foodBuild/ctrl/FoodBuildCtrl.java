package dr.foody.foodBuild.ctrl;

import dr.foody.calenderRec.dto.CalenderRecDto;
import dr.foody.foodBuild.svc.MealBuildSvc;
import dr.foody.foodBuild.svc.RerollMealSvc;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/foodBuild")
public class FoodBuildCtrl {
    @Autowired
    MealBuildSvc mealBuildService;

    @Autowired
    RerollMealSvc rerollMealService;

    @GetMapping("/getWeeklyMeals")
    public Object regInsDev(@RequestParam(value="idx",required=true) Integer userIdx ) {
        return mealBuildService.setFood(userIdx);
    }

    @GetMapping("/obtainRerolledMeal")
    public Object obtainRerolledMeal(@ModelAttribute CalenderRecDto calenderRecDto){
        Integer resultCode = rerollMealService.rerollMeal(calenderRecDto);
        JSONObject obj = new JSONObject();

        if (resultCode == -1){
            obj.put("rst_cd", resultCode);
            obj.put("rst_desc", "재추천을 위해 기존 식단을 조회해보았으나, 기존 식단이 검색되지 않았습니다. 직원에게 문의해주세요.");
        } else if (resultCode == -2) {
            obj.put("rst_cd", resultCode);
            obj.put("rst_desc", "잘못된 접근을 시도했습니다.");
        } else {
            obj.put("rst_cd", "200");
            obj.put("rst_desc", "식단 재추천을 마쳤습니다.");
        }

        return obj.toString();

        /*
        API : /foodBuild/obtainRerolledMeal
        호출방식 : GET
        Input : userIdx(유저 키idx 값), date(목표 날짜), occasion(목표 시간대)
        Output : "rst_cd" > 200: 성공
                            -1 : 실패, 조회된 건수가 없음 > 목표로 한 날짜+시간대 결과가 없음
                            -2 : 실패, 매개변수 이외의 다른 위험 값을 받았을 경우
                 "rst_desc" : 각 rst_cd에 따른 오류 설명
         */
    }
}
