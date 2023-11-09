package dr.foody.list_illness.dao;

import dr.foody.list_illness.dto.List_illnessDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface List_illnessDao {
    List<List_illnessDao> getList(List_illnessDto list_illnessDto);
    Integer mod(List_illnessDto list_illnessDto);
    Integer reg(List_illnessDto list_illnessDto);
    Integer del(Integer idx);
}
