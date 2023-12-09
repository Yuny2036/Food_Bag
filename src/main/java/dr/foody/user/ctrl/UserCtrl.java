package dr.foody.user.ctrl;

import dr.foody.user.dto.UserDto;
import dr.foody.user.dto.JoinDto;
import dr.foody.user.dto.PwdChangeDto;
import dr.foody.user.svc.UserSvc;
import dr.foody.userAllergie.dto.UserAllergieDto;
import dr.foody.userDisease.dto.UserDiseaseDto;
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
        HashMap<String, Object> userInfoMap = userService.login(userDto);
        String rst_cd = (String)userInfoMap.get("rst_cd");

        JSONObject obj = new JSONObject();

        obj.put("rst_cd", rst_cd);
        obj.put("user_idx", userInfoMap.get("userIdx"));
        obj.put("nickNm", userInfoMap.get("nickNm"));
        obj.put("allergieCd", userInfoMap.get("allergieCd"));
        obj.put("allergieNm", userInfoMap.get("allergieNm"));
        obj.put("allergieList", userInfoMap.get("allergieList"));
        obj.put("diseaseCd", userInfoMap.get("diseaseCd"));
        obj.put("diseaseNm", userInfoMap.get("diseaseNm"));
        return obj.toString();

        // API  : /user/login
        // 호출방식 : Get
        // Input : email(String), pwd(String)
        // output : rst_cd : -1 : 아이디 없음,   -2 : pw 틀림, 200 : 로그인 성공
        //          user_idx : 로그인된 user의 키값. 로그인이 실패라면 쓰레기 값이 input되어있음.
//                  nickNm : 로그인된 user의 닉네임
//                  allergieCd : user의 알레르기 코드 나열 (A#B#C 형식)
//                  allergieNm : user의 알레르기 이름 나열 (A#B#C 형식)
//                  allergieList : user의 알레르기 목록 (List 형식)
//                  diseaseCd : user의 만성질환 코드 나열 (A#B#C 형식)
//                  diseaseNm : user의 만성질환 이름 나열 (A#B#C 형식)

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

    @PostMapping("/checkpwdqa")
    public Object pwdChange(@ModelAttribute UserDto userDto){
        HashMap<String, String> response = userService.isPwdchangeAllowed(userDto);

        JSONObject obj = new JSONObject();
        obj.put("rst_cd", response.get("rst_cd"));
        obj.put("rst_desc", response.get("rst_desc"));

        return obj.toString();

        /*
        API : /user/checkpwdqa
        호출방식 : POST (확인만 하는 서비스지만 민감정보가 있어서 GET을 쓰지 않았음)
        Input : email (String, 유저user 테이블의 email), pwdq, pwda (유저user 테이블의 pwdq, pwda :: 비밀번호 질문답변)
        Output : "rst_cd" > -1: 계정정보 불일치
                            200 : 성공
                 "rst_desc" > 결과 설명
        */
    }

    @PutMapping("/replacepwd")
    public Object pwdReplace(@ModelAttribute UserDto userDto){
        HashMap<String, String> response = userService.replacePassword(userDto);

        JSONObject obj = new JSONObject();
        obj.put("rst_cd", response.get("rst_cd"));
        obj.put("rst_desc", response.get("rst_desc"));

        return obj.toString();

        /*
        API : /user/replacepwd
        호출방식 : PUT
        Input : email (String, 유저user 테이블의 email), pwd (유저user 테이블의 비밀번호pwd)
        Output : "rst_cd" > -3: 실행 중 오류 발생 (email 검색 결과 없음으로 추정, 제대로 보냈는지 확인)
                            200 : 성공
                 "rst_desc" > 결과 설명
        */
    }

    @PostMapping("/allergiechange")
    public Object allergieChange(@ModelAttribute UserAllergieDto userAllergieDto){
        HashMap<String, String> response = userService.allergieChange(userAllergieDto);

        JSONObject obj = new JSONObject();
        obj.put("rst_cd", response.get("rst_cd"));
        obj.put("deleted_records", response.get("deleted"));
        obj.put("added_records", response.get("added"));

        return obj.toString();

        /*
        API : /user/allergiechange
        호출방식 : POST
        Input : userIdx (Integer, user 테이블의 idx),
                code (String, user_allergie 테이블의 code, A#B#C 형태의 알레르기 코드)
        Output : "rst_cd" > 200: 성공
                 "deleted_records" > 지워진 레코드 갯수 (기존 알레르기)
                 "added_records" > 추가된 레코드 갯수 (새 알레르기)
         */
    }

    @PostMapping("/diseasechange")
    public Object diseaseChange(@ModelAttribute UserDiseaseDto userDiseaseDto){
        HashMap<String, String> response = userService.diseaseChange(userDiseaseDto);

        JSONObject obj = new JSONObject();
        obj.put("rst_cd", response.get("rst_cd"));
        obj.put("deleted_records", response.get("deleted"));
        obj.put("added_records", response.get("added"));

        return obj.toString();

        /*
        API : /user/diseasechange
        호출방식 : POST
        Input : userIdx (Integer, user 테이블의 idx),
                code (String, user_disease 테이블의 code, A#B#C 형태의 만성질환 코드)
        Output : "rst_cd" > 200: 성공
                 "deleted_records" > 지워진 레코드 갯수 (기존 만성질환)
                 "added_records" > 추가된 레코드 갯수 (새 만성질환)
         */
    }

    @GetMapping("/showmedicalstats")
    public Object showMedicalStats(@RequestParam(value = "idx", required = true) Integer idx){
        HashMap<String, String> response = userService.showMedicalstats(idx);

        JSONObject obj = new JSONObject();
        obj.put("rst_cd", response.get("rst_cd"));
        obj.put("allergie", response.get("allergie"));
        obj.put("disease", response.get("disease"));

        return obj.toString();
        /*
        API : /user/showmedicalstats
        호출방식 : GET
        Input : idx (Integer, user 테이블의 idx)
        Output : "rst_cd" > 200: 성공
                 "allergie" > A#B#C 의 형태로 전달 (유저 알레르기 정보)
                 "disease" > A#B#C 의 형태로 전달 (유저 만성질환 정보)
         */
    }

}
