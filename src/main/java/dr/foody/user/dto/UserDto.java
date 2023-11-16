package dr.foody.user.dto;

import lombok.Data;

@Data
public class UserDto {
    private Integer idx;
    private String email;
    private String nickname;
    private String pwd;
    private String pwdq;
    private String pwda;
}
