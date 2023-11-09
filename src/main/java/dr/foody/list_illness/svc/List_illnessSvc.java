package dr.foody.list_illness.svc;

import dr.foody.list_illness.dao.List_illnessDao;
import dr.foody.list_illness.dto.List_illnessDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class List_illnessSvc {
    @Autowired
    List_illnessDao list_illnessDao;

    public Object getList(List_illnessDto list_illnessDto){
        return list_illnessDao.getList(list_illnessDto);
    }
    public Object modify(List_illnessDto list_illnessDto){
        return list_illnessDao.mod(list_illnessDto);
    }
    public Object regist(List_illnessDto list_illnessDto){
        return list_illnessDao.reg(list_illnessDto);
    }
    public Object delete(Integer idx){
        return list_illnessDao.del(idx);
    }

}
