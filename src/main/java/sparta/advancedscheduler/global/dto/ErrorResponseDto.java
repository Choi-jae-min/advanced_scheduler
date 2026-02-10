package sparta.advancedscheduler.global.dto;

import lombok.Getter;

@Getter
public class ErrorResponseDto<T> implements ResponseDto<T> {
    private final String message;
    private final T data;
    public ErrorResponseDto( String message) {
        this.message = message;
        data = null;
    }

    public ErrorResponseDto( String message, T data) {
        this.message = message;
        this.data = data;
    }
}
