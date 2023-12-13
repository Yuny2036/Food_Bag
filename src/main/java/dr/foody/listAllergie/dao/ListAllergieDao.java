package dr.foody.listAllergie.dao;

import dr.foody.listAllergie.dto.ListAllergieDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ListAllergieDao {
    List<ListAllergieDto> getList(ListAllergieDto listAllergieDto);
    Integer mod(ListAllergieDto listAllergieDto);
    Integer reg(ListAllergieDto listAllergieDto);
    Integer del(Integer idx);
}
