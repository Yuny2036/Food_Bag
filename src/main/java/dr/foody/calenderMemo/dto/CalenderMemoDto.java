package dr.foody.calenderMemo.dto;

import lombok.Data;

@Data
public class CalenderMemoDto {
    private Integer idx;
    private Integer userIdx;
    private String text;
    private String date;
}
