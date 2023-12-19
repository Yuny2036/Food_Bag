package dr.foody.foodBuild.svc;

import dr.foody.foodBuild.dto.FoodBuildDto;
import dr.foody.food.dao.FoodDao;
import dr.foody.food.dto.FoodDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class FoodBuildSvc {
    @Autowired
    FoodDao foodDao;

    public List<FoodDto> makeKoreanFood(String allergie, String notInIngredient)
    {
        List<FoodDto> tmpList = new ArrayList<>();
        FoodDto mainFood = makeMain(allergie, notInIngredient);
        FoodDto soup = getFood("국물", allergie, notInIngredient);
        FoodDto kimchi = getFood("김치", allergie, notInIngredient);
        FoodDto bancahn1 = getFood("강반찬", allergie, notInIngredient);
        FoodDto bancahn2 = getFood("중반찬", allergie, notInIngredient);

        tmpList.add(mainFood);
        tmpList.add(soup);
        tmpList.add(kimchi);
        tmpList.add(bancahn1);
        tmpList.add(bancahn2);
        return tmpList;

    }

    //allergie : 알러지 정보. 콤마(,)로 구분해서 STring 형태로 코드를 입력한다. Food_allergie 테이블의 code 입력
    //           ex) "bean, shrimp, squid"
    // notInIngredient : food_ingredient 테이블의 ingre 컬럼 내용. 포함되지 않아야할 내용을 입력함.
                //ex) 위염환자가 자극적, 고기돼지 를 먹으면 안된다면 notInIngredient = "자극적, 고기돼지"  로 입력함
    public List<FoodDto> makeSoupFood(String allergie, String notInIngredient)
    {
        List<FoodDto> tmpList = new ArrayList<>();
        FoodDto soup = getFood("죽", allergie, notInIngredient);
        FoodDto kimchi = getFood("김치", allergie, notInIngredient);

        tmpList.add(soup);
        tmpList.add(kimchi);
        return tmpList;

    }

    public List<FoodDto> makeNoodleFood(String allergie, String notInIngredient)
    {
        List<FoodDto> tmpList = new ArrayList<>();
        FoodDto soup = getFood("국수", allergie, notInIngredient);
        FoodDto kimchi = getFood("김치", allergie, notInIngredient);

        tmpList.add(soup);
        tmpList.add(kimchi);
        return tmpList;

    }
    private void makeSoup(String posi, String nega)
    {

    }

    // allergie 구분은 , 로 입력   ex) 갑각류,복숭아
    // 주식을 선택하는 함수
    private FoodDto makeMain(String allergie, String notInIngre)
    {
        // 밥 80%, 볶음밥 20% 비율로 나오게 설정함.
        List<FoodBuildDto> tmpList = new ArrayList<>();

        FoodBuildDto rice = new FoodBuildDto();
        FoodBuildDto fried = new FoodBuildDto();
        rice.setWeight(80);
        rice.setName("밥");
        fried.setWeight(20);
        fried.setName("볶음밥");
        tmpList.add(rice);
        tmpList.add(fried);

        FoodBuildDto choiceFood = selectFoodRandomly(tmpList);


        return getFood(choiceFood.getName(), allergie, notInIngre);
    }



    private FoodDto getFood(String type, String allergie, String notInIngre)
    {
        FoodDto food = new FoodDto();
        food.setInType(type);
            food.setExceptAllergie(allergie);

        List<FoodDto> foodList = foodDao.getList(food);
        return selectRandomFood(foodList);
    }

    public static FoodBuildDto selectFoodRandomly(List<FoodBuildDto> foodList) {
        Random random = new Random();
        int totalWeight = foodList.stream().mapToInt(FoodBuildDto::getWeight).sum();
        int randomNumber = random.nextInt(totalWeight) + 1;

        for (FoodBuildDto food : foodList) {
            if (randomNumber <= food.getWeight()) {
                return food;
            }
            randomNumber -= food.getWeight();
        }

        // 만약 위에서 선택되지 않았다면 (이 부분이 실행되면 안됨)
        throw new IllegalStateException("Food selection logic error");
    }


    public static FoodDto selectRandomFood(List<FoodDto> foodList) {
        if (foodList == null || foodList.isEmpty()) {
            throw new IllegalArgumentException("Food list is empty or null");
        }

        Random random = new Random();
        int randomIndex = random.nextInt(foodList.size());

        return foodList.get(randomIndex);
    }


}
