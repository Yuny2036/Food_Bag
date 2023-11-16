package dr.foody.userAllergie.svc;

import dr.foody.userAllergie.dao.UserAllergieDao;
import dr.foody.userAllergie.dto.UserAllergieDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserAllergieSvc {
    @Autowired
    UserAllergieDao userAllergieDao;

    public Object getList(UserAllergieDto userAllergieDto){
        return userAllergieDao.getList(userAllergieDto);
    }
    public Object modify(UserAllergieDto userAllergieDto){
        return userAllergieDao.mod(userAllergieDto);
    }
    public Object regist(UserAllergieDto userAllergieDto){
        return userAllergieDao.reg(userAllergieDto);
    }
    public Object delete(Integer idx){
        return userAllergieDao.del(idx);
    }
}
