package dr.foody.calenderMemo.dao;

import dr.foody.calenderMemo.dto.CalenderMemoDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CalenderMemoDao {
    List<CalenderMemoDto> getList(CalenderMemoDto calenderMemoDto);
    Integer mod(CalenderMemoDto calenderMemoDto);
    Integer reg(CalenderMemoDto calenderMemoDto);
    Integer del(Integer idx);
}
