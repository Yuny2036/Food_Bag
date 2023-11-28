package dr.foody.calenderRec.svc;

import dr.foody.calenderRec.dao.CalenderRecDao;
import dr.foody.calenderRec.dto.CalenderRecDto;
import dr.foody.food.dao.FoodDao;
import dr.foody.food.dto.FoodDto;
import dr.foody.user.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
public class CalenderRecSvc {
    @Autowired
    CalenderRecDao calenderRecDao;

    @Autowired
    FoodDao foodDao;

    public Object getList(CalenderRecDto calenderRecDto){
        return calenderRecDao.getList(calenderRecDto);
    }
    public Object modify(CalenderRecDto calenderRecDto){
        return calenderRecDao.mod(calenderRecDto);
    }
    public Object regist(CalenderRecDto calenderRecDto){
        return calenderRecDao.reg(calenderRecDto);
    }
    public Object delete(Integer idx){
        return calenderRecDao.del(idx);
    }

    public HashMap<String, String> getRecommendedList(UserDto userDto){
//        Return 할 HashMap 생성
        HashMap<String, String> resultMap = new HashMap();

//        캘린더-추천식단목록 조회할 회원정보 설정
        CalenderRecDto calender = new CalenderRecDto();
        calender.setUserIdx(userDto.getIdx());

//        캘린더-추천식단목록 조회
        List<CalenderRecDto> recList = calenderRecDao.getList(calender);
        StringBuilder recFoodNm = new StringBuilder();

//        조회된 건수가 0일 경우 오류 확인하라고 이야기 할 것
        if (recList.isEmpty()){
            resultMap.put("rst_cd", "-1");
            resultMap.put("rst_desc", "해당 계정으로 조회된 추천식단이 없습니다. 문의해주세요.");
            return resultMap;
        }

// /*        음식테이블 음식 조회할 고유번호 설정 */
//        FoodDto foods = new FoodDto();
//        String f;
//
// /*        조회된 음식을 #음식이름#음식이름#음식이름 식으로 포장 */
//        for (CalenderRecDto c : recList){
//            recFoodNm.append("#");
//            foods.setIdx(c.getFoodIdx());
//            f = foods.getName();
//            recFoodNm.append(f);
//        }

        FoodDto foodLoad = new FoodDto();

        for (CalenderRecDto c : recList){
            foodLoad.setIdx(c.getFoodIdx());
            List<FoodDto> food = foodDao.getList(foodLoad);
            recFoodNm.append("#");
            for (FoodDto d : food){
                recFoodNm.append(d.getName());
            }
        }

//        맨 앞의 #를 잘라내고 HashMap 으로 전달하기
        resultMap.put("rst_cd", "200");
        resultMap.put("foodName", recFoodNm.substring(1));

        return resultMap;
    }
}
