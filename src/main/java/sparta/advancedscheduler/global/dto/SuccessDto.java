package sparta.advancedscheduler.global.dto;

import lombok.Getter;

@Getter
public class SuccessDto<T> implements ResponseDto<T> {
    private final String message;
    private final T data;

    public SuccessDto(T data , String message) {
        this.message = message;
        this.data = data;
    }
}
