package dr.foody.userDisease.svc;

import dr.foody.userDisease.dao.UserDiseaseDao;
import dr.foody.userDisease.dto.UserDiseaseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserDiseaseSvc {
    @Autowired
    UserDiseaseDao userDiseaseDao;

    public Object getList(UserDiseaseDto userDiseaseDto){
        return userDiseaseDao.getList(userDiseaseDto);
    }
    public Object modify(UserDiseaseDto userDiseaseDto){
        return userDiseaseDao.mod(userDiseaseDto);
    }
    public Object regist(UserDiseaseDto userDiseaseDto){
        return userDiseaseDao.reg(userDiseaseDto);
    }
    public Object delete(Integer idx){
        return userDiseaseDao.del(idx);
    }
}
