package sparta.advancedscheduler.comment.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ResponseCommentDto {
    private final String comment;
    private final String poster;
}
