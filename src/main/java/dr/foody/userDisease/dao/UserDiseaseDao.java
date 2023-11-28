package dr.foody.userDisease.dao;

import dr.foody.userDisease.dto.UserDiseaseDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDiseaseDao {
    List<UserDiseaseDto> getList(UserDiseaseDto userDiseaseDto);
    Integer mod(UserDiseaseDto userDiseaseDto);
    Integer reg(UserDiseaseDto userDiseaseDto);
    Integer del(Integer idx);
}
