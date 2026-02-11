package sparta.advancedscheduler.global.dto;

import org.springframework.data.domain.Page;

public interface ResponseDto <T>{

    static <T> ResponseDto<T> error(String message){
        return new ErrorResponseDto<>(message);
    }

    static <T> ResponseDto<T> errorWithData(T data ,String message){
        return new ErrorResponseDto<>(message, data);
    }

    static <T> ResponseDto<T> success(T data , String message){
        return new SuccessDto<>(data ,message);
    }

    static <T> ResponseDto<T> pagination(Page<T> data , String message){
        return new PageResponseDto<>(
                data.getNumber(),
                data.getSize(),
                data.getContent(),
                data.getTotalPages(),
                data.getTotalElements(),
                message
        );
    }
}
