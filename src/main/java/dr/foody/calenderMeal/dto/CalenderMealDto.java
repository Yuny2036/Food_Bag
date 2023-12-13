package dr.foody.calenderMeal.dto;

import lombok.Data;

@Data
public class CalenderMealDto {
    private Integer idx;
    private Integer userIdx;
    private String occasion;
    private String date;
    private Integer foodIdx;
}
