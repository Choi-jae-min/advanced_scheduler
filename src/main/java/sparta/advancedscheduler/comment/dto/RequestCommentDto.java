package sparta.advancedscheduler.comment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class RequestCommentDto {
    @NotBlank
    private String content;
    private Long scheduleId;
}
