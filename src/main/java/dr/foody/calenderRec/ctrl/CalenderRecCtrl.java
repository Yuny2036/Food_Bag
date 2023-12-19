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

    }
}
