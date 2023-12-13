package dr.foody.userQuestion.dto;

import lombok.Data;

@Data
public class UserQuestionDto {
//    userIdx 도 데이터로 받아야 할까?
    private Integer idx;
    private String email;
    private String subject;
    private String content;
}
