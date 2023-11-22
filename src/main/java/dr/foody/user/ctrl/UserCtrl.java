package dr.foody.user.ctrl;

import dr.foody.user.dto.UserDto;
import dr.foody.user.svc.UserSvc;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserCtrl {
    @Autowired
    UserSvc userService;

    @GetMapping("/getList")
    public Object getInsDevList(@ModelAttribute UserDto userDto) {
        return userService.getList(userDto);
    }

    @PostMapping("/regist")
    public Object regInsDev(@ModelAttribute UserDto userDto) {
        return userService.regist(userDto);
    }

    @DeleteMapping("/delete")
    public Object delInsDev(@RequestParam(value="idx",required=true) Integer idx) {
        return userService.delete(idx);
    }

    @PutMapping("/update")
    public Object modInsDev( @ModelAttribute UserDto userDto) {
        return userService.modify(userDto);
    }

    @GetMapping("/login")
    public Object login( @ModelAttribute UserDto userDto) {
        Integer userIdx = userService.login(userDto);
        String rst_cd ;
        if( userIdx > 0)
            rst_cd = "200";
        else
            rst_cd = userIdx.toString();

        JSONObject obj = new JSONObject();
        obj.put("rst_cd", rst_cd);
        obj.put("user_idx", userIdx);
        return obj.toString();

        // API  : /user/login
        // 호출방식 : Get
        // Input : email(String), pwd(String)
        // output : rst_cd : -1 : 아이디 없음,   -2 : pw 틀림, 200 : 로그인 성공
        //          user_idx : 로그인된 user의 키값. 로그인이 실패라면 쓰레기 값이 input되어있음.

    }
}
