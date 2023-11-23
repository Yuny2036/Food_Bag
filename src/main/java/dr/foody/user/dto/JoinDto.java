package dr.foody.user.dto;

import lombok.Data;

@Data
public class JoinDto {
    private Integer idx;
    private String email;
    private String nickname;
    private String pwd;
    private String pwdq;
    private String pwda;
    private String codeAlle;  // 알러지는 복수개 들어올수 있으며 '#'으로 구분한다.  A, B, C 라면 A#B#C 로 전달
    private String codeDise;  // 알러지는 복수개 들어올수 있으며 '#'으로 구분한다.  A, B, C 라면 A#B#C 로 전달
}
