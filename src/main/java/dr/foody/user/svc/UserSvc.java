package dr.foody.user.svc;

import dr.foody.user.dao.UserDao;
import dr.foody.user.dto.JoinDto;
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

    public HashMap<String, String> login(UserDto userDto){
        HashMap<String, String> resultMap = new HashMap<String, String>();
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

        HashMap<String, String> allergiInfoMap = getAllergieInfo(nowUser.getIdx());
        //HashMap<String, String> diseaseInfoMap = getDiseaseeInfo(userIdx);

        resultMap.put("rst_cd", "200");
        resultMap.put("userIdx", nowUser.getIdx().toString());
        resultMap.put("nickNm", nowUser.getNickname());
        resultMap.put("allergieCd", allergiInfoMap.get("allergieCd"));
        resultMap.put("allergieNm", allergiInfoMap.get("allergieNm"));
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

//    public Object regist(UserDto userDto){
//        return userDao.reg(userDto);
//    }

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
        UserDiseaseDto userDiseaseDto = new UserDiseaseDto();
        userDiseaseDto.setUserIdx(joinDto.getIdx());
        userDiseaseDto.setCode(joinDto.getCodeDise());
        if( userDiseaseDao.reg(userDiseaseDto) < 1)
            return false;

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


    private HashMap<String, String> getAllergieInfo(Integer userIdx)
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
        HashMap<String, String> resultMap = new HashMap<String, String>();
        resultMap.put("allergieCd", aCd.substring(1)) ;
        resultMap.put("allergieNm", aNm.substring(1)) ;

        return resultMap;

    }



}
