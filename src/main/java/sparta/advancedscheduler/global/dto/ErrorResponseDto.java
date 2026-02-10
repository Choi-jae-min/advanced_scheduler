package sparta.advancedscheduler.global.dto;

import lombok.Getter;

@Getter
public class ErrorResponseDto<T> implements ResponseDto<T> {
    private final String message;

    public ErrorResponseDto( String message) {
        this.message = message;
    }
}
