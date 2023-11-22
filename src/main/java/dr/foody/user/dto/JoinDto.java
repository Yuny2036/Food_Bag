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
    private String codeAlle;
    private String codeDise;
}
