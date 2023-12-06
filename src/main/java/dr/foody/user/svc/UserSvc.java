package dr.foody.user.svc;

import dr.foody.user.dao.UserDao;
import dr.foody.user.dto.JoinDto;
import dr.foody.user.dto.PwdChangeDto;
import dr.foody.user.dto.UserDto;
import dr.foody.userAllergie.dao.UserAllergieDao;
import dr.foody.userAllergie.dto.UserAllergieDto;
import dr.foody.userDisease.dao.UserDiseaseDao;
import dr.foody.userDisease.dto.UserDiseaseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Slf4j
@Service
public class UserSvc {
    @Autowired
    UserDao userDao;

    @Autowired
    UserDiseaseDao userDiseaseDao;

    @Autowired
    UserAllergieDao userAllergieDao;

    public Object getList(UserDto userDto){
        return userDao.getList(userDto);
    }

    public Object modify(UserDto userDto){
        return userDao.mod(userDto);
    }
    public Object regist(UserDto userDto){
        return userDao.reg(userDto);
    }
    public Object delete(Integer idx){
        return userDao.del(idx);
    }

    // login

    public HashMap<String, Object> login(UserDto userDto){
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        // return 값은 임의로 정의하였으며, 추후 return 값을 규정하여 진행하는것을 추천함
        // ex )  1 : 성공    1 이외의값은 실패이며 약속한 코드를 리턴함
        // ID 검사 하는 부분
        if(!isChkId(userDto.getEmail()))  // id가 없다면 -1 return
        {
            resultMap.put("rst_cd", "-1");
            return resultMap;
        }

        // pw 겁사하는 부분
        UserDto nowUser =  isChkPwd(userDto.getEmail(), userDto.getPwd());
        if(nowUser == null ) // id와 pw가 일치하는게 없다면 -2 return
        {
            resultMap.put("rst_cd", "-2");
            return resultMap;
        }

        HashMap<String, Object> allergiInfoMap = getAllergieInfo(nowUser.getIdx());
        HashMap<String, String> diseaseInfoMap = getDiseaseInfo(nowUser.getIdx());

        resultMap.put("rst_cd", "200");
        resultMap.put("userIdx", nowUser.getIdx().toString());
        resultMap.put("nickNm", nowUser.getNickname());
        resultMap.put("allergieCd", allergiInfoMap.get("allergieCd"));
        resultMap.put("allergieNm", allergiInfoMap.get("allergieNm"));
        resultMap.put("allergieList", allergiInfoMap.get("allergieList"));
        resultMap.put("diseaseCd", diseaseInfoMap.get("diseaseCd"));
        resultMap.put("diseaseNm", diseaseInfoMap.get("diseaseNm"));
        return resultMap;
    }

    // ID 를 검증하는 함수
    private Boolean isChkId(String email)
    {
        // email을 아이디로 쓴다고 가정함.
        UserDto userDto = new UserDto();

        userDto.setEmail(email);
        // user Table을 Select 함.
        List<UserDto> userList = userDao.getList(userDto);
        if(userList.size() == 0)// select한 row가 없다면..
            return false;  // 검색된 email이 없다면 회원가입이 안되어있다고 판단.
        else
            return true;  // 검색된 row가 있다면 아이디 존재
    }

    // passwd 를 검증 하는 함수
    private UserDto isChkPwd(String email, String pw)
    {
        // email을 아이디로 쓴다고 가정함.
        UserDto userDto = new UserDto();

        userDto.setEmail(email);
        userDto.setPwd(pw);
        // user Table을 Select 함.
        List<UserDto> userList = userDao.getList(userDto);
        if(userList.size() != 1)  // select한 row가 없다면..
            return null;  // email과 pw 모두 맞는게 없다면 -1을 return
        else
            return userList.get(0);  // 검색된 row가 있다면 아이디 키값을 리턴


    }
    /*
    비밀번호 찾기 질문 & 답변 대조 함수

    대조를 위해 받아야 할 값을 계정 index로 할 지 email을 앱 사용자가 직접 입력하게 해야 할 지 아직 못 정했다.
    아래 함수는 index와 pwda를 받아서 검증하게 만들었음.
     */
    private Boolean isPwdaCorrect(Integer idx, String pwda){
        UserDto userDto = new UserDto();

        userDto.setIdx(idx);
        userDto.setPwda(pwda);

        List<UserDto> userList = userDao.getList(userDto);

        if(userList.size() != 1){
            return false;
        } else {
            return true;
        }
    }


//    회원가입 시도
    public Integer join(JoinDto joinDto){

//        email 중복
        if (isChkId(joinDto.getEmail())){
            return -1;
        } else {
            if(allowJoin(joinDto))
                return 1;
            else
                return -1;
        }

    }


//    실제 회원가입 + 데이터 등록
    private Boolean allowJoin(JoinDto joinDto){

        // user table input
        UserDto userDto = new UserDto();
        userDto.setEmail(joinDto.getEmail());
        userDto.setNickname(joinDto.getNickname());
        userDto.setPwd(joinDto.getPwd());
        userDto.setPwdq(joinDto.getPwdq());
        userDto.setPwda(joinDto.getPwda());
        if(userDao.join(joinDto) < 1)
            return false;

        // userDisease Table input
        String disease = joinDto.getCodeDise();
        String[] arrDisease = disease.split("#");

        UserDiseaseDto userDiseaseDto = new UserDiseaseDto();
        userDiseaseDto.setUserIdx(joinDto.getIdx());

        for (String disea : arrDisease)
        {
            userDiseaseDto.setCode(disea);
            if( userDiseaseDao.reg(userDiseaseDto) < 1)
                return false;
        }


        //userAllergie table input
        String allergie = joinDto.getCodeAlle();  // A#B#C
        String[] arrAllergie = allergie.split("#");
        // arrAllergie[0] = A ,  arrAllergie[1] = B ....

        UserAllergieDto userAllergieDto = new UserAllergieDto();
        userAllergieDto.setUserIdx(joinDto.getIdx());

        for(String aller : arrAllergie)
        {
            userAllergieDto.setCode(aller);
            if(userAllergieDao.reg(userAllergieDto) < 1)
                return false;
        }


        return true;
    }


//    회원 알러지 정보 가져오기
    private HashMap<String, Object> getAllergieInfo(Integer userIdx)
    {
        UserAllergieDto aDto = new UserAllergieDto();
        aDto.setUserIdx(userIdx);
        List<UserAllergieDto> userAllergieList = userAllergieDao.getList(aDto);
        StringBuilder aCd = new StringBuilder();
        StringBuilder aNm = new StringBuilder();

        for(UserAllergieDto u : userAllergieList)
        {
            aCd.append("#");
            aCd.append(u.getCode());

            aNm.append("#");
            aNm.append(u.getName());
        }
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("allergieCd", aCd.substring(1)) ;
        resultMap.put("allergieNm", aNm.substring(1)) ;
        resultMap.put("allergieList", userAllergieList) ;
        return resultMap;

    }

//    회원 만성질환 정보 가져오기
    private HashMap<String, String> getDiseaseInfo(Integer userIdx)
    {
        UserDiseaseDto aDto = new UserDiseaseDto();
        aDto.setUserIdx(userIdx);
        List<UserDiseaseDto> userDiseaseList = userDiseaseDao.getList(aDto);
        StringBuilder aCd = new StringBuilder();
        StringBuilder aNm = new StringBuilder();

        for(UserDiseaseDto u : userDiseaseList)
        {
            aCd.append("#");
            aCd.append(u.getCode());

            aNm.append("#");
            aNm.append(u.getName());
        }
        HashMap<String, String> resultMap = new HashMap<String, String>();
        resultMap.put("diseaseCd", aCd.substring(1)) ;
        resultMap.put("diseaseNm", aNm.substring(1)) ;

        return resultMap;

    }


//    회원탈퇴
    public HashMap<String, String> resign(Integer idx){
        HashMap<String, String> resultMap = new HashMap<>();
        UserAllergieDto theUserAlle = new UserAllergieDto();
        UserDiseaseDto theUserDise = new UserDiseaseDto();

        theUserAlle.setUserIdx(idx);
        theUserDise.setUserIdx(idx);

        List<UserAllergieDto> alleList = userAllergieDao.getList(theUserAlle);
        List<UserDiseaseDto> diseList = userDiseaseDao.getList(theUserDise);

        for (UserAllergieDto a : alleList){
            Integer i = a.getIdx();
            userAllergieDao.del(i);
        }
        for (UserDiseaseDto b : diseList){
            Integer i = b.getIdx();
            userDiseaseDao.del(i);
        }

//        계정정보만 지울거라면 이 위의 코드를 주석

        resultMap.put("rst_cd", "200");
        userDao.del(idx);

        return resultMap;
    }

    public HashMap<String, String> pwdChange(PwdChangeDto pwdChangeDto){
        HashMap<String, String> resultMap = new HashMap<>();

//        pwda 대조
        if(!isPwdaCorrect(pwdChangeDto.getIdx(), pwdChangeDto.getPwda())){
            resultMap.put("rst_cd", "-2");
            resultMap.put("rst_desc", "비밀번호 질문에 대한 답변이 일치하지 않습니다.");
            return resultMap;
        }

        UserDto userDto = new UserDto();
        userDto.setIdx(pwdChangeDto.getIdx());
        userDto.setPwd(pwdChangeDto.getNewPwd());

        userDao.mod(userDto);
        resultMap.put("rst_cd", "200");
        resultMap.put("rst_desc", "성공적으로 비밀번호를 변경했습니다.");
        return resultMap;

    }

//    알레르기 변경
    public HashMap<String, String> allergieChange(UserAllergieDto userAllergieDto){
        HashMap<String, String> resultMap = new HashMap<>();

        Integer rst_delete = allergieDelete(userAllergieDto.getUserIdx());
        Integer rst_add = allergieAdd(userAllergieDto.getUserIdx(), userAllergieDto.getCode());

        resultMap.put("rst_cd", "200");
        resultMap.put("deleted", rst_delete.toString());
        resultMap.put("added", rst_add.toString());

        return resultMap;
    }

//    회원번호와 일치하는 알레르기 레코드 전부 삭제
    private Integer allergieDelete(Integer idx){
        Integer deleteCount = 0;
        UserAllergieDto userAllergieDto = new UserAllergieDto();
        userAllergieDto.setUserIdx(idx);

        List<UserAllergieDto> alleList = userAllergieDao.getList(userAllergieDto);
        for (UserAllergieDto alle : alleList){
            Integer i = alle.getIdx();
            userAllergieDao.del(i);
            deleteCount++;
        }

        return deleteCount;
    }

// 프론트에서 넘어온 A#B#C 형태의 알레르기 가공하여 DB에 레코드 추가
    private Integer allergieAdd(Integer idx, String allergie){
        Integer addCount = 0;
        UserAllergieDto userAllergieDto = new UserAllergieDto();
        String[] arrayAllergie = allergie.split("#");

        for (String a : arrayAllergie){
            userAllergieDto.setUserIdx(idx);
            userAllergieDto.setCode(a);
            userAllergieDao.reg(userAllergieDto);
            addCount++;
        }

        return addCount;
    }

//    만성질환 변경
    public HashMap<String, String> diseaseChange(UserDiseaseDto userDiseaseDto){
        HashMap<String, String> resultMap = new HashMap<>();

        Integer rst_delete = diseaseDelete(userDiseaseDto.getUserIdx());
        Integer rst_add = diseaseAdd(userDiseaseDto.getUserIdx(), userDiseaseDto.getCode());

        resultMap.put("rst_cd", "200");
        resultMap.put("deleted", rst_delete.toString());
        resultMap.put("added", rst_add.toString());

        return resultMap;
    }

//    회원번호와 일치하는 만성질환 레코드 전부 삭제

    private Integer diseaseDelete(Integer idx){
        Integer deleteCount = 0;
        UserDiseaseDto userDiseaseDto = new UserDiseaseDto();
        userDiseaseDto.setUserIdx(idx);

        List<UserDiseaseDto> diseList = userDiseaseDao.getList(userDiseaseDto);
        for (UserDiseaseDto dise : diseList){
            Integer i = dise.getIdx();
            userDiseaseDao.del(i);
            deleteCount++;
        }

        return deleteCount;
    }

// 프론트에서 넘어온 A#B#C 형태의 만성질환 가공하여 DB에 레코드 추가
    private Integer diseaseAdd(Integer idx, String disease){
        Integer addCount = 0;
        UserDiseaseDto userDiseaseDto = new UserDiseaseDto();
        String[] arrayDisease = disease.split("#");

        for (String a : arrayDisease){
            userDiseaseDto.setUserIdx(idx);
            userDiseaseDto.setCode(a);
            userDiseaseDao.reg(userDiseaseDto);
            addCount++;
        }

        return addCount;
    }

//    알레르기 + 만성질환 return.. 만들기는 했지만, login 단계에서 정보 다 넘기는데 이게 필요한가?
    public HashMap<String, String> showMedicalstats(Integer idx){
        HashMap<String, String> totalInfo = new HashMap<>();
        HashMap<String, Object> alleInfo = getAllergieInfo(idx);
        HashMap<String, String> diseInfo = getDiseaseInfo(idx);

        totalInfo.put("allergie", (String)alleInfo.get("allergieNm"));
        totalInfo.put("disease", diseInfo.get("diseaseNm"));
        totalInfo.put("rst_cd", "200");

        return totalInfo;
    }
}
