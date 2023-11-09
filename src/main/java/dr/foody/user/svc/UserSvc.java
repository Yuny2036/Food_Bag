package dr.foody.user.svc;

import dr.foody.user.dao.UserDao;
import dr.foody.user.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
