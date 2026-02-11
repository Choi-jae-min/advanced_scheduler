package sparta.advancedscheduler.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class RequestScheduleDto {
    @NotBlank
    @Size(min = 2, max = 10 , message = "제목은 2~10글자 이내로 작성해주세요.")
    String title;
    @NotBlank
    @Size(max = 200 , message = "일정은 200자 이내로 작성해주세요.")
    String content;
}
