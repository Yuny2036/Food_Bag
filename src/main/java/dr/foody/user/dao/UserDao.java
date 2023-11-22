package dr.foody.user.dao;

import dr.foody.user.dto.UserDto;
import dr.foody.user.dto.JoinDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {
    List<UserDto> getList(UserDto userDto);
    Integer join(JoinDto joinDto);
    Integer mod(UserDto userDto);
    Integer reg(UserDto userDto);
    Integer del(Integer idx);
}
