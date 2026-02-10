package sparta.advancedscheduler.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class RequestScheduleDto {
    @NotBlank
    String title;
    @NotBlank
    String content;
    @NotBlank
    String poster;
}
