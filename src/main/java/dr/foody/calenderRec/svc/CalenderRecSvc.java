package dr.foody.calenderRec.svc;

import dr.foody.calenderRec.dao.CalenderRecDao;
import dr.foody.calenderRec.dto.CalenderRecDto;
import dr.foody.calenderRec.dto.NameOnlyRmdDto;
import dr.foody.food.dao.FoodDao;
import dr.foody.food.dto.FoodDto;
import dr.foody.user.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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

    public HashMap<String, Object> getRecommendedList(UserDto userDto){
//        Return 할 HashMap 생성
        HashMap<String, Object> resultMap = new HashMap();

//        현재 날짜 설정하기
        LocalTime time = LocalTime.now();
        LocalDate currentDate = LocalDate.now();
        if (isBetween(time, LocalTime.of(22, 0), LocalTime.of(23, 59, 59))) {
            // 22시부터 23:59분까지는 다음 날로 간주
            currentDate = currentDate.plusDays(1);
        }

//        캘린더-추천식단목록 조회할 회원정보 설정
        CalenderRecDto calender = new CalenderRecDto();
        calender.setUserIdx(userDto.getIdx()); // 회원 키 값
        calender.setDate(currentDate.toString());

//        아침 점심 저녁 시간대에 맞춰서 검색하기
        if (isBetween(time, LocalTime.of(22, 0), LocalTime.of(23, 59, 59))
                || isBetween(time, LocalTime.of(0, 0), LocalTime.of(9, 59, 59))) {
            calender.setOccasion("아침");
        } else if (isBetween(time, LocalTime.of(10, 0), LocalTime.of(15, 59, 59))) {
            calender.setOccasion("점심");
        } else if (isBetween(time, LocalTime.of(16, 0), LocalTime.of(21, 59, 59))) {
            calender.setOccasion("저녁");
        } else {
            resultMap.put("rst_cd", "-5");
            resultMap.put("rst_desc", "기기 오류");
            return resultMap;
        }


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
        resultMap.put("foodList", recList);

        return resultMap;
    }

    private boolean isBetween(LocalTime time, LocalTime start, LocalTime end){
        return !time.isBefore(start) && !time.isAfter(end);
    }

    /*

    public void searchRmdMeal(CalenderRecDto calenderRecDto){
        List<CalenderRecDto> searchResult = calenderRecDao.searchDateList(dateSearchDto);
        LocalDate dateStart = LocalDate.parse(dateSearchDto.getDateStart());
        LocalDate dateEnd = LocalDate.parse(dateSearchDto.getDateEnd());

        if (dateStart.compareTo(dateEnd) > 0){
//            시작날짜가 종료날짜보다 이후이므로 오류코드 반환할 것
            return;
        }

        HashMap<String, Object> resultMap = new HashMap<>();
        HashMap<String, Object> resultMapInner = new HashMap<>();

        do {

            for (Integer i = 0; i < searchResult.size(); i++){

            }

            dateStart = dateStart.plusDays(1);
        } while(dateStart != dateEnd);

    }
     */

    public HashMap<String, Object> searchRmdMeal(CalenderRecDto calenderRecDto) {
//        return calenderRecDao.searchDateList(calenderRecDto);

        HashMap<String, Object> resultMap = new HashMap<>();
        List<NameOnlyRmdDto> mealList = calenderRecDao.searchDateList(calenderRecDto);

        if (mealList.isEmpty()){
            resultMap.put("rst_cd", "-1");
            resultMap.put("rst_desc", "검색결과가 없습니다. 유저, 날짜형식(yyyy-MM-dd)과 시간대('아침', '점심', '저녁')이 올바른지 확인해주세요.");
            return resultMap;
        }

//        ArrayList로 넘기는 방법
        ArrayList<String> resultArray = new ArrayList<>();

        for (NameOnlyRmdDto names : mealList){
            resultArray.add(names.getName());
        }

        resultMap.put("rst_cd", "200");
        resultMap.put("rst_desc", "전달 성공");
        resultMap.put("foodList", resultArray);


        return resultMap;

        /*
//        배열(Array)로 넘기는 방법

        String[] resultArray = new String[mealList.size()];

        for (Integer i = 0; i < mealList.size(); i++){
            resultArray[i] = mealList.get(i).getName();
        }
         */
    }
}
