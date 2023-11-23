package dr.foody.userAllergie.dao;

import dr.foody.userAllergie.dto.UserAllergieDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserAllergieDao {
    List<UserAllergieDto> getList(UserAllergieDto userAllergieDto);
    Integer mod(UserAllergieDto userAllergieDto);
    Integer reg(UserAllergieDto userAllergieDto);
    Integer del(Integer idx);
}
