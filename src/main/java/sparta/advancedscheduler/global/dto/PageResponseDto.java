package sparta.advancedscheduler.global.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class PageResponseDto<T> implements ResponseDto<T> {
    private final int page;
    private final int size;
    private final List<T> items;
    private final int totalPages;
    private final Long totalElements;
    private final String message;

}
