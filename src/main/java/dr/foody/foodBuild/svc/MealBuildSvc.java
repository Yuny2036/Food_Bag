package dr.foody.foodBuild.svc;

import dr.foody.userDisease.ctrl.UserDiseaseCtrl;
import org.apache.catalina.User;

import dr.foody.calenderRec.dao.CalenderRecDao;
import dr.foody.calenderRec.dto.CalenderRecDto;
import dr.foody.food.dto.FoodDto;
import dr.foody.userDisease.dao.UserDiseaseDao;
import dr.foody.userDisease.dto.UserDiseaseDto;
import dr.foody.user.dao.UserDao;
import dr.foody.user.dto.UserDto;
import dr.foody.userAllergie.dao.UserAllergieDao;
import dr.foody.userAllergie.dto.UserAllergieDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.time.DayOfWeek;
import java.time.LocalDate;

@Slf4j
@Service
public class MealBuildSvc {

    // gast : 위염에 대한 정의
    Integer gastSoupMax = 2;
    Integer gastSoupRandom = 49;
    Integer gastNoodleMax = 2;
    Integer gastNoodleRandom = 90;
    String gastIngre = "자극적";
    Integer gastIngreRandom = 70;

    // diab : 당뇨에 대한 정의
    Integer diabSoupMax = 1;
    Integer diabSoupRandom = 80;
    Integer diabNoodleMax = 1;
    Integer diabNoodleRandom = 80;
    String diabIngre = "정제곡물";
    Integer diabIngreRandom = 70;

    // hidp : 고혈압에 대한 정의
    Integer hidpIngreRandom = 80;
    String hidpIngre = "자극적";

    @Autowired
    FoodBuildSvc foodBuildSvc;
    @Autowired
    UserDao userDao;
    @Autowired
    UserDiseaseDao userDiseaseDao;
    @Autowired
    UserAllergieDao userAllergieDao;
    @Autowired
    CalenderRecDao calenderRecDao;

    public void test(){

    }


    public Integer setFood(Integer userIdx)
    {
        List<FoodDto>[][] foodList = makeFood(userIdx);

        // 현재 날짜 구하기
        LocalDate today = LocalDate.now();

        // 이번 주의 월요일 날짜 계산
        LocalDate nowDate = today.with(DayOfWeek.MONDAY);

        ArrayList<CalenderRecDto> cList = new ArrayList<CalenderRecDto>();

        for (int i = 0; i < foodList.length; i++) {
            for (int j = 0; j < foodList[i].length; j++) {
                for(FoodDto f : foodList[i][j])
                {
                    CalenderRecDto c = new CalenderRecDto();
                    c.setFoodIdx(f.getIdx());
                    c.setUserIdx(userIdx);
                    c.setDate(nowDate.toString());
                    if(j == 0)
                        c.setOccasion("아침");
                    else if(j == 1)
                        c.setOccasion("점심");
                    else //if(j == 2)
                        c.setOccasion("저녁");
                    cList.add(c);
                }
            }
            nowDate = nowDate.plusDays(1);
        }
        return calenderRecDao.regList(cList);
    }



    public List<FoodDto>[][] getDefaultCook(String allergie, Integer notInIngrePer, String notInIngredient)
    {

        List<FoodDto>[][] myArray = new List[7][3];
        // 평범한 식단 1주일치 출력. 자극적 태그 70%확률로 제외
        for (int i = 0; i < myArray.length; i++) {
            for (int j = 0; j < myArray[i].length; j++) {
                myArray[i][j] = foodBuildSvc.makeKoreanFood(allergie, generateWithProbability(notInIngrePer, notInIngredient));
            }
        }
        return myArray;
    }



    public List<FoodDto>[][] makeFood(Integer userIdx)
    {

        // 무조건 월~일  식단을 출력함
        // 월 : 0, 화 : 1, .... 일 :6
        // 아침 : 0, 점심 :1, 저녁 2
        List<FoodDto>[][] myArray = new List[7][3];


        List<UserDiseaseDto> userDiseaseList = getUserDisease(userIdx);
        String allergie = getUserAllergie(userIdx);
        String notInIngredient = null;


        Integer soupRandom = 0; //죽 확률
        Integer soupMax = 0;  // 죽 최대
        Integer noodleRandom = 0;   // 국수 확률
        Integer noodleMax = 0;  // 국수 최대

        Boolean isObes = false;  //비만
        Boolean isHidp = false;  // 고혈압
        Boolean isGast = false;  // 위염
        Boolean isDiab = false;  // 당뇨
        // 질병별 식단 수정
        for(UserDiseaseDto d : userDiseaseList)
        {
            if(d.getCode().equals("diab")) {
                soupRandom = soupRandom < diabSoupRandom ? diabSoupRandom : soupRandom;
                soupMax = soupMax < diabSoupMax ? diabSoupMax : soupMax;

                noodleRandom = noodleRandom < diabNoodleRandom ? diabNoodleRandom : noodleRandom;
                noodleMax = noodleMax > diabNoodleMax ? diabNoodleMax : soupMax;
                isDiab = true;
            }
            else  if(d.getCode().equals("gast")) {
                soupRandom = soupRandom < gastSoupRandom ? gastSoupRandom : soupRandom;
                soupMax = soupMax < gastSoupMax ? gastSoupMax : soupMax;

                noodleRandom = noodleRandom < gastNoodleRandom ? gastNoodleRandom : noodleRandom;
                noodleMax = noodleMax > gastNoodleMax ? gastNoodleMax : soupMax;
                isGast = true;
            }
            else  if(d.getCode().equals("obes"))
                isObes = true;
            else  if(d.getCode().equals("hibp"))
                isHidp = true;
        }

        // 고혈압인경우 100%로 자극적을 제거하고 추후 점심만 자극적 3회 80 추가해줌.
        // 위염인경우 전체식단에서 자극적 식단을 70%로 배제
        // 나머지는 기본 식단 제공
        String ingre;

        if(isHidp)
            myArray = getDefaultCook(allergie, 100, hidpIngre);
        else if(isGast)
            myArray = getDefaultCook(allergie, gastIngreRandom, "'"+gastIngre+"', '"+diabIngre+"'");
        else if(isDiab)
            myArray = getDefaultCook(allergie, diabIngreRandom, "'"+gastIngre+"', '"+diabIngre+"'");
        else
            myArray = getDefaultCook(allergie, 0, "자극적");

        //죽 제공이 있을경우 죽 식단 추가
        for(int i =soupMax; i>0; i--) {
            if (generateWithProbability(soupRandom, "죽") != null) {
                String[] arrIndex = ((String) getRandomElement(myArray)).split("#");
                myArray[Integer.parseInt(arrIndex[0])][Integer.parseInt(arrIndex[1])] = foodBuildSvc.makeSoupFood(allergie, generateWithProbability(0, notInIngredient));
            }
        }

        //국수 제공이 있을경우 국수식단 추가
        for(int i =noodleMax; i>0; i--) {
            if (generateWithProbability(noodleRandom, "국수") != null) {

                String[] arrIndex = ((String) getRandomElement(myArray)).split("#");
                Integer tmp = Integer.parseInt(arrIndex[1]);
                if(tmp == 0)
                    tmp ++;   // 0은 아침밥인데 아침밥에 국수는 ... 피하기로

                myArray[Integer.parseInt(arrIndex[0])][tmp] = foodBuildSvc.makeNoodleFood(allergie, generateWithProbability(0, notInIngredient));
            }
        }

        //고혈압인경우 자극적인 태그 80확률로 점심 수정해줌
        if(isHidp) {
            for (int i = 3; i > 0; i--) {
                String[] arrIndex = ((String) getRandomElement(myArray)).split("#");
                // 점심 식사는 한식 자극적 80% 확률로 대체
                myArray[Integer.parseInt(arrIndex[0])][1] = foodBuildSvc.makeKoreanFood(allergie, generateWithProbability(hidpIngreRandom, hidpIngre));
            }
        }
        return myArray;
    }


    private UserDto getUserInfo(Integer userIdx)
    {
        UserDto userDto = new UserDto();
        userDto.setIdx(userIdx);
        List<UserDto> userList = userDao.getList(userDto);
        if(userList.size() == 0)
            return null;

        return userList.get(0);
    }


    private List<UserDiseaseDto> getUserDisease(Integer userIdx)
    {
        UserDiseaseDto userDiseaseDto = new UserDiseaseDto();
        userDiseaseDto.setUserIdx(userIdx);
        return userDiseaseDao.getList(userDiseaseDto);
    }



    // return 값은 알러지 코드를 콤마로 가져옴.
    private String getUserAllergie(Integer userIdx)
    {
        UserAllergieDto userAllergieDto = new UserAllergieDto();
        userAllergieDto.setUserIdx(userIdx);
        List<UserAllergieDto> allergiList =userAllergieDao.getList(userAllergieDto);
        if(allergiList.size() == 0)
            return null;

        StringBuilder allerggie = new StringBuilder();
        for(UserAllergieDto u : allergiList) {
            allerggie.append("'");
            allerggie.append(u.getCode());
            allerggie.append("'");
            allerggie.append(",");
        }
        // allerggie 마지막 쉼표 제거
        if (allerggie.length() > 0) {
            allerggie.deleteCharAt(allerggie.length() - 1);
        }

        return allerggie.toString();
    }




    // probability 확률로 txt를 리턴함
    public static String generateWithProbability(int probability, String txt) {
        if (probability < 0 || probability > 100) {
            throw new IllegalArgumentException("Invalid probability");
        }

        Random random = new Random();
        int randomValue = random.nextInt(100) + 1; // 1부터 100 사이의 랜덤값

        return randomValue <= probability ? txt : null;
    }



    // 랜덤하게 한 요소를 뽑는 방법
    public static String getRandomElement(List<FoodDto>[][] array) {
        if (array == null || array.length == 0 || array[0].length == 0) {
            throw new IllegalArgumentException("Invalid array");
        }

        Random random = new Random();
        int randomRow = random.nextInt(array.length);
        int randomCol = random.nextInt(array[0].length);

        return randomRow+"#"+randomCol;
    }

}
