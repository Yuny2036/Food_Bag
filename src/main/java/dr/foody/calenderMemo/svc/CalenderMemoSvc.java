package dr.foody.calenderMemo.svc;

import dr.foody.calenderMemo.dao.CalenderMemoDao;
import dr.foody.calenderMemo.dto.CalenderMemoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CalenderMemoSvc {

    @Autowired
    CalenderMemoDao calenderMemoDao;

    public Object getList(CalenderMemoDto calenderMemoDto){
        return calenderMemoDao.getList(calenderMemoDto);
    }
    public Object modify(CalenderMemoDto calenderMemoDto){
        return calenderMemoDao.mod(calenderMemoDto);
    }
    public Object regist(CalenderMemoDto calenderMemoDto){
        return calenderMemoDao.reg(calenderMemoDto);
    }
    public Object delete(Integer idx){
        return calenderMemoDao.del(idx);
    }
}
