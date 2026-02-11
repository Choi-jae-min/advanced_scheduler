package sparta.advancedscheduler.schedule.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ResponseScheduleListDto {
    private final String title;
    private final String content;
    private final String poster;
    private final long commentCount;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
}
