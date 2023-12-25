package dr.foody.foodBuild.svc;

import dr.foody.calenderRec.dao.CalenderRecDao;
import dr.foody.calenderRec.dto.CalenderRecDto;
import dr.foody.food.dto.FoodDto;
import dr.foody.foodBuild.dto.RerollMealDto;
import dr.foody.userAllergie.dao.UserAllergieDao;
import dr.foody.userAllergie.dto.UserAllergieDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class RerollMealSvc {
    @Autowired
    CalenderRecDao calenderRecDao;

    @Autowired
    UserAllergieDao userAllergieDao;

    @Autowired
    FoodBuildSvc foodBuildService;

    private String notInIngredientReroll = " ";

    @Transactional
    public Integer rerollMeal(CalenderRecDto rerollMealDto){
        if (rerollMealDto.getFoodIdx() != null || rerollMealDto.getIdx() != null){
//            무결성한 쿼리를 위해, 음식 키 혹은 테이블 키가 들어간 경우 중단
            return -2;
        }

//        기존 식단 검색
//        CalenderRecDto previousRmd = new CalenderRecDto();
//        previousRmd.setUserIdx(rerollMealDto.getUserIdx());
//        previousRmd.setDate(rerollMealDto.getDate());
//        previousRmd.setOccasion(rerollMealDto.getOccasion());
//        List<CalenderRecDto> previousResults = calenderRecDao.getList(previousRmd);
        List<CalenderRecDto> previousResults = calenderRecDao.getList(rerollMealDto);

        if (previousResults.isEmpty()){
            return -1;
//            식단 조회 결과 없음, 이 경우 프론트에서 제공한 파라미터 값을 다시 점검해야 함
        }

//        검색된 기존 식단 삭제
        for (CalenderRecDto pR : previousResults){
            calenderRecDao.del(pR.getIdx());
        }

//        사용자의 정보(알레르기) 정의
        UserAllergieDto userAllergie = new UserAllergieDto();
        userAllergie.setUserIdx(rerollMealDto.getUserIdx());
        List<UserAllergieDto> userAllergies = userAllergieDao.getList(userAllergie);
        StringBuilder userAllergieQuery = new StringBuilder();

        for (UserAllergieDto uA : userAllergies){
            userAllergieQuery.append(",");
            userAllergieQuery.append("'");
            userAllergieQuery.append(uA.getCode());
            userAllergieQuery.append("'");
        }

//        식단 재추천하기
        List<FoodDto> rerollMeal = foodBuildService.makeKoreanFood(userAllergieQuery.substring(1), notInIngredientReroll);

//        재추천받은 식단 DB 추천테이블에 넣을 수 있게 쿼리 작성
        List<CalenderRecDto> rerollMealList = new ArrayList<>();
        for (FoodDto rM : rerollMeal){
            CalenderRecDto c = new CalenderRecDto();
            c.setUserIdx(rerollMealDto.getUserIdx());
            c.setDate(rerollMealDto.getDate());
            c.setOccasion(rerollMealDto.getOccasion());
            c.setFoodIdx(rM.getIdx());

            rerollMealList.add(c);
        }

//        생성
        return calenderRecDao.regList(rerollMealList);
    }
}
