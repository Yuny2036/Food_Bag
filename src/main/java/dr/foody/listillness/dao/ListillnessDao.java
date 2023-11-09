package dr.foody.listillness.dao;

import dr.foody.listillness.dto.ListillnessDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ListillnessDao {
    List<ListillnessDao> getList(ListillnessDto listillnessDto);
    Integer mod(ListillnessDto listillnessDto);
    Integer reg(ListillnessDto listillnessDto);
    Integer del(Integer idx);
}
