package dr.foody.listillness.svc;

import dr.foody.listillness.dao.ListillnessDao;
import dr.foody.listillness.dto.ListillnessDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ListillnessSvc {
    @Autowired
    ListillnessDao listillnessDao;

    public Object getList(ListillnessDto listillnessDto){
        return listillnessDao.getList(listillnessDto);
    }
    public Object modify(ListillnessDto listillnessDto){
        return listillnessDao.mod(listillnessDto);
    }
    public Object regist(ListillnessDto listillnessDto){
        return listillnessDao.reg(listillnessDto);
    }
    public Object delete(Integer idx){
        return listillnessDao.del(idx);
    }

}
