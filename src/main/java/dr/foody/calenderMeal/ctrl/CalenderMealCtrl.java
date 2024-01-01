package dr.foody.calenderMeal.ctrl;

import dr.foody.calenderMeal.dto.CalenderMealDto;
import dr.foody.calenderMeal.dto.CalenderRecordUserDto;
import dr.foody.calenderMeal.svc.CalenderMealSvc;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/calenderMeal")
public class CalenderMealCtrl {
    @Autowired
    CalenderMealSvc calenderMealService;

    @GetMapping("/getList")
    public Object getInsDevList(@ModelAttribute CalenderMealDto calenderMealDto) {
        return calenderMealService.getList(calenderMealDto);
    }

    @PostMapping("/regist")
    public Object regInsDev(@ModelAttribute CalenderMealDto calenderMealDto) {
        return calenderMealService.regist(calenderMealDto);
    }

    @DeleteMapping("/delete")
    public Object delInsDev(@RequestParam(value="idx",required=true) Integer idx) {
        return calenderMealService.delete(idx);
    }

    @PutMapping("/update")
    public Object modInsDev( @ModelAttribute CalenderMealDto calenderMealDto) {
        return calenderMealService.modify(calenderMealDto);
    }

    @PostMapping("/writeMealRecords")
    public Object writeMealRecords(@ModelAttribute CalenderRecordUserDto calenderRecordUserDto){
        HashMap<String, Object> response = (HashMap<String, Object>) calenderMealService.rewriteRecords(calenderRecordUserDto);

        JSONObject obj = new JSONObject();
        obj.put("rst_cd", response.get("rst_cd"));
        obj.put("rst_desc", response.get("rst_desc"));

        return obj.toString();

        /*
        API : /calenderMeal/writeMealRecords
        호출방식 : POST
        Input : userIdx(유저 키idx 값), date(날짜), occasion(시간대), foodRecord(음식)(',' 로 구분해서 보내야 함)
        Output : "rst_cd" > 200: 성공
                            -2 : foodRecord 를 받은 것이 없음 > 추가되지 않음
                            -3 : foodRecord 로 들어온 음식이름이 결과적으로 모두 의미가 없는 공백이었을 경우 > 추가되지 않음
                 "rst_desc" : 각 rst_cd에 따른 오류 설명
         */
    }
}
