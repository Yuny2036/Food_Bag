package dr.foody.calenderMeal.dto;

import lombok.Data;

import java.util.List;

@Data
public class CalenderRecordUserDto {
    private Integer userIdx;
    private String date;
    private String occasion;
    private List<String> foodRecord;
}
