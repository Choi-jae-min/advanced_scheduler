package sparta.advancedscheduler.user.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ResponseUserDto {
    private final String username;
    private final String email;
}
