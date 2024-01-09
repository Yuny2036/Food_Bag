package dr.foody.calenderRec.dao;

import dr.foody.calenderRec.dto.CalenderRecDto;
import dr.foody.calenderRec.dto.NameOnlyRmdDto;
import dr.foody.calenderRec.dto.NameIdxRmdDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CalenderRecDao {
    List<CalenderRecDto> getList(CalenderRecDto calenderRecDto);
    Integer mod(CalenderRecDto calenderRecDto);
    Integer reg(CalenderRecDto calenderRecDto);
    Integer regList(List<CalenderRecDto> calenderRecDto);
    List<NameIdxRmdDto> searchDateList(CalenderRecDto calenderRecDto);
    Integer del(Integer idx);

    Integer delResign(Integer userIdx);
}
