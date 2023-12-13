package dr.foody.userQuestion.ctrl;

import dr.foody.userQuestion.dto.UserQuestionDto;
import dr.foody.userQuestion.svc.UserQuestionSvc;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/userquestion")
public class UserQuestionCtrl {
    @Autowired
    UserQuestionSvc userQuestionService;

    @GetMapping("/getList")
    public Object getInsDevList(@ModelAttribute UserQuestionDto userQuestionDto) {
        return userQuestionService.getList(userQuestionDto);
    }

    @PostMapping("/regist")
    public Object regInsDev(@ModelAttribute UserQuestionDto userQuestionDto) {
        return userQuestionService.regist(userQuestionDto);
    }

    @DeleteMapping("/delete")
    public Object delInsDev(@RequestParam(value="idx",required=true) Integer idx) {
        return userQuestionService.delete(idx);
    }

    @PutMapping("/update")
    public Object modInsDev( @ModelAttribute UserQuestionDto userQuestionDto) {
        return userQuestionService.modify(userQuestionDto);
    }

    @PostMapping("/submitQuestion")
    public Object submitQuestion(@ModelAttribute UserQuestionDto userQuestionDto){
        HashMap<String, Object> response = userQuestionService.submitQuestion(userQuestionDto);

        JSONObject obj = new JSONObject();
        obj.put("rst_cd", response.get("rst_cd"));
        obj.put("rst_desc", response.get("rst_desc"));

        return obj.toString();
    }
}
