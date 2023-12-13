package dr.foody.foodDisease.dao;

import dr.foody.foodDisease.dto.FoodDiseaseDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FoodDiseaseDao {
    List<FoodDiseaseDto> getList(FoodDiseaseDto foodDiseaseDto);
    Integer mod(FoodDiseaseDto foodDiseaseDto);
    Integer reg(FoodDiseaseDto foodDiseaseDto);
    Integer del(Integer idx);
}
