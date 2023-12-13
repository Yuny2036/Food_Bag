package dr.foody.userQuestion.dao;

import dr.foody.userQuestion.dto.UserQuestionDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserQuestionDao {
    List<UserQuestionDto> getList(UserQuestionDto userQuestionDto);
    Integer mod(UserQuestionDto userQuestionDto);
    Integer reg(UserQuestionDto userQuestionDto);
    Integer del(Integer idx);
}
