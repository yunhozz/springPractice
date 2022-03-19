package loginpractice.web.login;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginForm {

    /**
     * validation 을 위한 @NotBlank 추가
     */
    @NotBlank
    private String loginId;

    @NotBlank
    private String loginPw;
}
