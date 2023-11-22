package dr.foody.user.svc;

import dr.foody.user.dao.UserDao;
import dr.foody.user.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserSvc {
    @Autowired
    UserDao userDao;

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
    public Integer login(UserDto userDto){

        // return 값은 임의로 정의하였으며, 추후 return 값을 규정하여 진행하는것을 추천함
        // ex )  1 : 성공    1 이외의값은 실패이며 약속한 코드를 리턴함
        // ID 검사 하는 부분
        if(!isChkId(userDto.getEmail()))  // id가 없다면 -1 return
            return -1;

        // pw 겁사하는 부분
        Integer userIdx = isChkPwd(userDto.getEmail(), userDto.getPwd());
        if(userIdx < 0) // id와 pw가 일치하는게 없다면 -2 return
            return -2;

        return userIdx;
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
    private Integer isChkPwd(String email, String pw)
    {
        // email을 아이디로 쓴다고 가정함.
        UserDto userDto = new UserDto();

        userDto.setEmail(email);
        userDto.setPwd(pw);
        // user Table을 Select 함.
        List<UserDto> userList = userDao.getList(userDto);
        if(userList.size() == 0)  // select한 row가 없다면..
            return -1;  // email과 pw 모두 맞는게 없다면 -1을 return
        else
            return userList.get(0).getIdx();  // 검색된 row가 있다면 아이디 키값을 리턴


    }

}
