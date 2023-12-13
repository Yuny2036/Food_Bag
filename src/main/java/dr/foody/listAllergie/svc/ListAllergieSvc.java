package dr.foody.listAllergie.svc;

import dr.foody.listAllergie.dao.ListAllergieDao;
import dr.foody.listAllergie.dto.ListAllergieDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ListAllergieSvc {
    @Autowired
    ListAllergieDao listAllergieDao;

    public Object getList(ListAllergieDto listAllergieDto){
        return listAllergieDao.getList(listAllergieDto);
    }
    public Object modify(ListAllergieDto listAllergieDto){
        return listAllergieDao.mod(listAllergieDto);
    }
    public Object regist(ListAllergieDto listAllergieDto){
        return listAllergieDao.reg(listAllergieDto);
    }
    public Object delete(Integer idx){
        return listAllergieDao.del(idx);
    }
}
