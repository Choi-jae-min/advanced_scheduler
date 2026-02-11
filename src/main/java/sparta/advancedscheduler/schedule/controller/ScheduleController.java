package sparta.advancedscheduler.schedule.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import sparta.advancedscheduler.auth.service.AuthorizationService;
import sparta.advancedscheduler.global.dto.ResponseDto;
import sparta.advancedscheduler.schedule.dto.*;
import sparta.advancedscheduler.schedule.service.ScheduleService;

@RestController()
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final AuthorizationService authorizationService;
    @PostMapping
    public ResponseDto<Long> createSchedule(
            @Valid
            @RequestBody RequestScheduleDto requestScheduleDto,
            @CookieValue(name = "SESSION") String sessionId
    ) {
        Long userId = authorizationService.validateSession(sessionId);
        Long createdScheduleId = scheduleService.createSchedule(userId ,requestScheduleDto);
        return ResponseDto.success(createdScheduleId , "성공적으로 일정을 생성하였습니다.");
    }

    @GetMapping
    public ResponseDto<ResponseScheduleListDto> getSchedules(
            @CookieValue(name = "SESSION") String sessionId,
            @PageableDefault(size = 10, page = 0) Pageable pageable
    ) {
        Long userId = authorizationService.validateSession(sessionId);

        Page<ResponseScheduleListDto> scheduleListDtos = scheduleService.findScheduleListByName(userId, pageable);
        return ResponseDto.pagination(scheduleListDtos , "성공적으로 조회 하였습니다.");
    }

    @GetMapping("{scheduleId}")
    public ResponseDto<ResponseScheduleDto> getSchedule(@PathVariable Long scheduleId) {
        ResponseScheduleDto scheduleDto = scheduleService.findScheduleListById(scheduleId);

        return ResponseDto.success(scheduleDto , "성공적으로 조회 하였습니다.");
    }

    @PatchMapping("{scheduleId}")
    public ResponseDto<ResponseScheduleDto> updateSchedule(
            @Valid
            @PathVariable Long scheduleId,
            @RequestBody RequestScheduleUpdateDto requestScheduleUpdateDto,
            @CookieValue(name = "SESSION") String sessionId
    ) {
        Long userId = authorizationService.validateSession(sessionId);
        ResponseScheduleDto scheduleDto = scheduleService.updateScheduleById(userId,scheduleId , requestScheduleUpdateDto);
        return ResponseDto.success(scheduleDto , "성공적으로 수정 하였습니다.");
    }

    @DeleteMapping("{scheduleId}")
    public ResponseDto<Long> deleteSchedule(
            @PathVariable Long scheduleId,
            @CookieValue(name = "SESSION") String sessionId
            ) {
        Long userId = authorizationService.validateSession(sessionId);
        Long deletedId = scheduleService.deleteSchedule(userId, scheduleId);
        return ResponseDto.success(deletedId , "성공적으로 삭제 하였습니다.");
    }
}
