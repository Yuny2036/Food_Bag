package dr.foody.calenderMeal.svc;

import dr.foody.calenderMeal.dao.CalenderMealDao;
import dr.foody.calenderMeal.dto.CalenderMealDto;
import dr.foody.calenderMeal.dto.CalenderRecordUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
public class CalenderMealSvc {
    @Autowired
    CalenderMealDao calenderMealDao;

    public Object getList(CalenderMealDto calenderMealDto){
        return calenderMealDao.getList(calenderMealDto);
    }
    public Object modify(CalenderMealDto calenderMealDto){
        return calenderMealDao.mod(calenderMealDto);
    }
    public Object regist(CalenderMealDto calenderMealDto){
        return calenderMealDao.reg(calenderMealDto);
    }
    public Object delete(Integer idx){
        return calenderMealDao.del(idx);
    }

/*
    @Transactional
    public HashMap<String, Object> writeMealRecords(List<CalenderMealDto> calenderMealDtos, Integer userIndex){
        HashMap<String, Object> resultMap = new HashMap<>();

//        기존 식단 검색 후에 삭제하기



//        아무 내용도 없는지 확인하기
        if (calenderMealDtos.isEmpty()){
            resultMap.put("rst_cd", "-2");
            resultMap.put("rst_desc", "추가할 식단기록을 받지 못했습니다. 기존 식단기록은 제거하였습니다.");
            return resultMap;
        } else {
            CalenderMealDto targetForDelete = new CalenderMealDto();
            targetForDelete.setUserIdx(calenderMealDtos.get(0).getUserIdx());
            targetForDelete.setDate(calenderMealDtos.get(0).getDate());
            targetForDelete.setOccasion(calenderMealDtos.get(0).getOccasion());

            calenderMealDao.deleteRecordedMeals(targetForDelete);
        }

//        새 정보를 받아서 추가하기
//        for (CalenderMealDto cM : calenderMealDtos){
//            CalenderMealDto newMeal = new CalenderMealDto();
//            newMeal.setUserIdx(cM.getUserIdx());
//            newMeal.setDate(cM.getDate());
//            newMeal.setOccasion(cM.getOccasion());
//            newMeal.setFoodRecord(cM.getFoodRecord());
//
//            calenderMealDao.addRecordedMeals(newMeal);
//        }

        calenderMealDao.addRecordedMeals(calenderMealDtos);
        resultMap.put("rst_cd", "200");
        resultMap.put("rst_desc", "기존 내역을 삭제한 뒤, 요청한 내역을 등록하는 데 성공했습니다.");
        return resultMap;
    }
 */

    public Object rewriteRecords(CalenderRecordUserDto calenderRecordUserDto){
        deleteMealRecords(calenderRecordUserDto);
        return writeMealRecords(calenderRecordUserDto);
    }

    private Integer deleteMealRecords(CalenderRecordUserDto calenderRecordUserDto){
//        HashMap<String, Object> resultMap = new HashMap<>();
//        기존 데이터 삭제하기
        CalenderMealDto previousMeal = new CalenderMealDto();
        previousMeal.setUserIdx(calenderRecordUserDto.getUserIdx());
        previousMeal.setDate(calenderRecordUserDto.getDate());
        previousMeal.setOccasion(calenderRecordUserDto.getOccasion());
        return calenderMealDao.deleteRecordedMeals(previousMeal);
    }

    private HashMap<String, Object> writeMealRecords(CalenderRecordUserDto calenderRecordUserDto){
        HashMap<String, Object> resultMap = new HashMap<>();

//        새 음식 목록 유효성 검사하기 1 : 기록이 전혀 없음
        if (calenderRecordUserDto.getFoodRecord().isEmpty()){
            resultMap.put("rst_cd", "-2");
            resultMap.put("rst_desc", "전달받은 식사 목록이 없습니다. 식사 기록을 추가하지 않았습니다.");
            return resultMap;
        }

//        쿼리로 쓸 ArrayList 선언
        ArrayList<CalenderMealDto> newRecordsArray = new ArrayList<>();

//        공통정보를 사전에 대입하기
        CalenderMealDto newRecord = new CalenderMealDto();
        newRecord.setUserIdx(calenderRecordUserDto.getUserIdx());
        newRecord.setDate(calenderRecordUserDto.getDate());
        newRecord.setOccasion(calenderRecordUserDto.getOccasion());
        for (String f : calenderRecordUserDto.getFoodRecord()){
//            앞뒤 공백 제거
            f = f.trim();
//            새 음식 목록 유효성 검사하기 2 : 기록이 들어는 왔는데, 의미가 없는 공백으로 들어옴.
            if (f.isEmpty()) continue;

//            유효성 검사에 통과하면 foodRecord에 넣고 ArrayList에 추가
            newRecord.setFoodRecord(f);
            newRecordsArray.add(newRecord);
        }

//        유효성 검사 2에서 검사결과 모든 문자열이 의미없는 공백이였을 경우
        if (newRecordsArray.isEmpty()){
            resultMap.put("rst_cd", "-3");
            resultMap.put("rst_desc", "유효한 문자열이 들어오지 않았습니다. 식사기록을 추가하지 않았습니다.");
            return resultMap;
        }

//        식사기록 추가하고 결과 반환
        calenderMealDao.addRecordedMeals(newRecordsArray);
        resultMap.put("rst_cd", "200");
        resultMap.put("rst_desc", "식사기록을 추가하였습니다.");
        return resultMap;
    }

}
