package dr.foody.calenderRec.ctrl;

import dr.foody.calenderRec.dto.CalenderRecDto;
import dr.foody.calenderRec.svc.CalenderRecSvc;
import dr.foody.user.dto.UserDto;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/calenderRecommend")
public class CalenderRecCtrl {
    @Autowired
    CalenderRecSvc calenderRecService;

    @GetMapping("/getList")
    public Object getInsDevList(@ModelAttribute CalenderRecDto calenderRecDto) {
        return calenderRecService.getList(calenderRecDto);
    }

    @PostMapping("/regist")
    public Object regInsDev(@ModelAttribute CalenderRecDto calenderRecDto) {
        return calenderRecService.regist(calenderRecDto);
    }

    @DeleteMapping("/delete")
    public Object delInsDev(@RequestParam(value="idx",required=true) Integer idx) {
        return calenderRecService.delete(idx);
    }

    @PutMapping("/update")
    public Object modInsDev( @ModelAttribute CalenderRecDto calenderRecDto) {
        return calenderRecService.modify(calenderRecDto);
    }

    @GetMapping("/recommended")
    public Object recommended(@ModelAttribute UserDto userDto){
//        #으로 묶인 식단리스트 받아오기
        HashMap<String, Object> recommenedInfo = calenderRecService.getRecommendedList(userDto);

//        JSON으로 API의 Call을 반환하기
        JSONObject obj = new JSONObject();
        obj.put("rst_cd", recommenedInfo.get("rst_cd"));
        obj.put("rst_desc", recommenedInfo.get("rst_desc"));
        obj.put("foodName", recommenedInfo.get("foodName"));

        return obj.toString();

        /*
        API : /calenderRecommend/recommended
        호출방식 : GET
        Input : userIdx(유저 키idx 값)
        Output : "rst_cd" > 200: 성공
                            -1 : 실패, 조회된 건수가 없음 = 검색결과로 나오는 식사가 없음
                            -5 : 실패, 기기 오류(시각 측정 관련)
                 "rst_desc" : 각 rst_cd에 따른 오류 설명
                 "foodName" : # 단위로 묶인 식단
                 "foodList" : List로 보낸 식단
         */
    }

    @GetMapping("/searchRmdMeal")
    public Object searchRmdMeal(@ModelAttribute CalenderRecDto calenderRecDto){
        HashMap<String, Object> response = calenderRecService.searchRmdMeal(calenderRecDto);
        JSONObject obj = new JSONObject();

        obj.put("rst_cd", response.get("rst_cd"));
        obj.put("rst_desc", response.get("rst_desc"));
        obj.put("foodList", response.get("foodList"));

        return obj.toString();

        /*
        API : /calenderRecommend/searchRmdMeal
        호출방식 : GET
        Input : userIdx(유저 키idx 값), date(찾는 날짜), occasion(아침/점심/저녁 중 하나)
        Output : "rst_cd" > 200: 성공
                            -1 : 실패, 유저키/날짜/식사시각 중 입력이 잘못된 값이 있는지 확인
                 "rst_desc" : 각 rst_cd에 따른 오류 설명
                 "foodList" : List로 보낸 식단
         */
    }
}
