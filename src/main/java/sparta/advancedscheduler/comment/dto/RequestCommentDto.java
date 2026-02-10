package sparta.advancedscheduler.comment.dto;

import lombok.Getter;

@Getter
public class RequestCommentDto {
    private String content;
    private Long scheduleId;
}
