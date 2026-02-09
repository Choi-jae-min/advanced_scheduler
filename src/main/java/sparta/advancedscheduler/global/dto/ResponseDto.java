package sparta.advancedscheduler.global.dto;

public interface ResponseDto <T>{
    static <T> ResponseDto<T> success(T data , String message){
        return new SuccessDto<>(data ,message);
    }
}
