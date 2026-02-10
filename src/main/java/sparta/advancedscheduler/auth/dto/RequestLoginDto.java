package sparta.advancedscheduler.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class RequestLoginDto {
    @NotBlank
    @Email(message = "올바른 형식이아닙니다.")
    private String email;

    @NotBlank
    @Size(min = 8 , message = "비밀번호는 8글자 이상이어야합니다.")
    private String password;
}
