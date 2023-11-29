package dr.foody.user.ctrl;

import dr.foody.user.dto.UserDto;
import dr.foody.user.dto.JoinDto;
import dr.foody.user.dto.PwdChangeDto;
import dr.foody.user.svc.UserSvc;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

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
        HashMap<String, String> userInfoMap = userService.login(userDto);
        String rst_cd = userInfoMap.get("rst_cd");

        JSONObject obj = new JSONObject();

        obj.put("rst_cd", rst_cd);
        obj.put("user_idx", userInfoMap.get("userIdx"));
        obj.put("nickNm", userInfoMap.get("nickNm"));
        obj.put("allergieCd", userInfoMap.get("allergieCd"));
        obj.put("allergieNm", userInfoMap.get("allergieNm"));
        obj.put("diseaseCd", userInfoMap.get("diseaseCd"));
        obj.put("diseaseNm", userInfoMap.get("diseaseNm"));
        return obj.toString();

        // API  : /user/login
        // 호출방식 : Get
        // Input : email(String), pwd(String)
        // output : rst_cd : -1 : 아이디 없음,   -2 : pw 틀림, 200 : 로그인 성공
        //          user_idx : 로그인된 user의 키값. 로그인이 실패라면 쓰레기 값이 input되어있음.

    }

    @PostMapping("/join")
    public Object join( @ModelAttribute JoinDto joinDto){
        Integer svcResponse = userService.join(joinDto);
        String rst_cd;

        if (svcResponse < 0){
            rst_cd = svcResponse.toString();
        } else {
            rst_cd = "200";   // 200 success
        }

        JSONObject obj = new JSONObject();
        obj.put("rst_cd", rst_cd);
        return obj.toString();

        /*
        API : /user/join
        호출방식 : POST
        Input : email, pwd, nickname, pwdq, pwda, codeAlle, codeDise (모두 String)
        Output : "rst_cd" > 200: 성공, -1: 같은 이메일을 가진 계정 있음
         */
    }

    @DeleteMapping("/resign")
    public Object resign(@RequestParam(value = "idx", required = true) Integer idx){
        HashMap<String, String> resignResponse = userService.resign(idx);
        String rst_cd = resignResponse.get("rst_cd");

        JSONObject obj = new JSONObject();
        obj.put("rst_cd", rst_cd);

        return obj.toString();

        /*
        API : /user/resign
        호출방식 : DELETE
        Input : idx (Integer, 유저user 테이블의 idx)
        Output : "rst_cd" > 200: 성공
         */
    }

    @PutMapping("/pwdchange")
    public Object pwdChange(@ModelAttribute PwdChangeDto pwdChangeDto){
        HashMap<String, String> pwdCResponse = userService.pwdChange(pwdChangeDto);

        JSONObject obj = new JSONObject();
        obj.put("rst_cd", pwdCResponse.get("rst_cd"));
        obj.put("rst_desc", pwdCResponse.get("rst_desc"));

        return obj.toString();
    }

}
