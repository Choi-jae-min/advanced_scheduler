package sparta.advancedscheduler.schedule.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ResponseScheduleDto {
    private final String title;
    private final String content;
    private final String poster;
    private final LocalDateTime createdDate;
    private final LocalDateTime updatedDate;
}
