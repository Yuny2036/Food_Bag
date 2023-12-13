package dr.foody.foodNutri.svc;

import dr.foody.foodNutri.dao.FoodNutriDao;
import dr.foody.foodNutri.dto.FoodNutriDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
public class FoodNutriSvc {
    @Autowired
    FoodNutriDao foodNutriDao;

    public Object getList(FoodNutriDto foodNutriDto){
        return foodNutriDao.getList(foodNutriDto);
    }

    public Object modify(FoodNutriDto foodNutriDto){
        return foodNutriDao.mod(foodNutriDto);
    }
    public Object regist(FoodNutriDto foodNutriDto){
        return foodNutriDao.reg(foodNutriDto);
    }
    public Object delete(Integer idx){
        return foodNutriDao.del(idx);
    }

    public HashMap<String, Object> getNutritionMap(FoodNutriDto foodNutriDto){
        HashMap<String, Object> resultMap = new HashMap<>();
        FoodNutriDto targetFood = new FoodNutriDto();
        targetFood.setFoodIdx(foodNutriDto.getFoodIdx());

        List<FoodNutriDto> targetList = foodNutriDao.getList(targetFood);
        if (targetList.size() == 0){
            resultMap.put("rst_cd", "-1");
            resultMap.put("rst_desc", "유효하지 않은 음식 코드를 불렀습니다. 담당자에게 문의해주세요.");
            return resultMap;
        }

        FoodNutriDto resultFood = targetList.get(0);
        Float kcal = resultFood.getKcal();
        Float carbo = resultFood.getCarbo();
        Float protein = resultFood.getProtein();
        Float fat = resultFood.getFat();
        Float salt = resultFood.getSalt();
        Float chole = resultFood.getChole();

        resultMap.put("rst_cd", "200");
        resultMap.put("rst_desc", "입력한 food_idx 값으로 검색한 음식의 영양성분 데이터(float)입니다.");
        resultMap.put("kcal", kcal);
        resultMap.put("carbo", carbo);
        resultMap.put("protein", protein);
        resultMap.put("fat", fat);
        resultMap.put("salt", salt);
        resultMap.put("chole", chole);

        return resultMap;
    }
}
