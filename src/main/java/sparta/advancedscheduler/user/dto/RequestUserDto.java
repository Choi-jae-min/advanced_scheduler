package sparta.advancedscheduler.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class RequestUserDto {
    @NotBlank
    private String username;
    @Email(message = "이메일형식이 아닙니다.")
    private String email;

    @NotBlank
    @Size(min = 8 , message = "비밀번호는 8글자 이상이어야합니다.")
    private String password;
}
