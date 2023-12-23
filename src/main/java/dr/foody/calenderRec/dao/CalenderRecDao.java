package dr.foody.calenderRec.dao;

import dr.foody.calenderRec.dto.CalenderRecDto;
import dr.foody.calenderRec.dto.NameOnlyRmdDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CalenderRecDao {
    List<CalenderRecDto> getList(CalenderRecDto calenderRecDto);
    Integer mod(CalenderRecDto calenderRecDto);
    Integer reg(CalenderRecDto calenderRecDto);
    Integer regList(List<CalenderRecDto> calenderRecDto);
    List<NameOnlyRmdDto> searchDateList(CalenderRecDto calenderRecDto);
    Integer del(Integer idx);
}
