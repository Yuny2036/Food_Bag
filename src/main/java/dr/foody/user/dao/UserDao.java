package dr.foody.user.dao;

import dr.foody.user.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {
    List<UserDao> getList(UserDto userDto);
    Integer mod(UserDto userDto);
    Integer reg(UserDto userDto);
    Integer del(Integer idx);
}
