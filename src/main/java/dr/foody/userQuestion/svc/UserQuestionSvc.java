package dr.foody.userQuestion.svc;

import dr.foody.user.dao.UserDao;
import dr.foody.user.dto.UserDto;
import dr.foody.userQuestion.dao.UserQuestionDao;
import dr.foody.userQuestion.dto.UserQuestionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
public class UserQuestionSvc {
    @Autowired
    UserQuestionDao userQuestionDao;

    @Autowired
    UserDao userDao;

    public Object getList(UserQuestionDto userQuestionDto){
        return userQuestionDao.getList(userQuestionDto);
    }
    public Object modify(UserQuestionDto userQuestionDto){
        return userQuestionDao.mod(userQuestionDto);
    }
    public Object regist(UserQuestionDto userQuestionDto){
        return userQuestionDao.reg(userQuestionDto);
    }
    public Object delete(Integer idx){
        return userQuestionDao.del(idx);
    }

    public HashMap<String, Object> submitQuestion(UserQuestionDto userQuestionDto){
        HashMap<String, Object> resultMap = new HashMap<>();
        UserQuestionDto newQuestion = new UserQuestionDto();

        UserDto userDto = new UserDto();
        userDto.setEmail(userQuestionDto.getEmail());
        List<UserDto> userList = userDao.getList(userDto);
        if (userList.isEmpty()){
            resultMap.put("rst_cd", "-1");
            resultMap.put("rst_desc", "존재하지 않는 이메일의 계정입니다. 확인해주세요.");
            return resultMap;
        }

        if (userQuestionDto.getSubject() == null){
            resultMap.put("rst_cd", "-2");
            resultMap.put("rst_desc", "제목을 입력하지 않았습니다. 확인해주세요.");
            return resultMap;
        }

        if (userQuestionDto.getContent() == null){
            resultMap.put("rst_cd", "-3");
            resultMap.put("rst_desc", "내용을 입력하지 않았습니다. 확인해주세요.");
            return resultMap;
        }

        newQuestion.setEmail(userQuestionDto.getEmail());
        newQuestion.setSubject(userQuestionDto.getSubject());
        newQuestion.setContent(userQuestionDto.getContent());

        userQuestionDao.reg(newQuestion);
        resultMap.put("rst_cd", "200");
        resultMap.put("rst_desc", "문의가 정상적으로 등록되었습니다.");
        return resultMap;
    }
}
