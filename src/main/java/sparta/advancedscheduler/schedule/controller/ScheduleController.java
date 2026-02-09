package sparta.advancedscheduler.schedule.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sparta.advancedscheduler.global.dto.ResponseDto;
import sparta.advancedscheduler.schedule.dto.RequestScheduleDto;
import sparta.advancedscheduler.schedule.dto.RequestScheduleUpdateDto;
import sparta.advancedscheduler.schedule.dto.ResponseScheduleDto;
import sparta.advancedscheduler.schedule.dto.ResponseScheduleListDto;
import sparta.advancedscheduler.schedule.service.ScheduleService;

import java.util.List;

@RestController()
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseDto<Long> createSchedule(@RequestBody RequestScheduleDto requestScheduleDto) {
        Long createdScheduleId = scheduleService.createSchedule(requestScheduleDto);
        return ResponseDto.success(createdScheduleId , "성공적으로 일정을 생성하였습니다.");
    }

    @GetMapping
    public ResponseDto<List<ResponseScheduleListDto>> getSchedules(@RequestParam String poster) {
        List<ResponseScheduleListDto> scheduleListDtos = scheduleService.findScheduleListByName(poster);

        return ResponseDto.success(scheduleListDtos , "성공적으로 조회 하였습니다.");
    }

    @GetMapping("{scheduleId}")
    public ResponseDto<ResponseScheduleDto> getSchedule(@PathVariable Long scheduleId) {
        ResponseScheduleDto scheduleDto = scheduleService.findScheduleListById(scheduleId);

        return ResponseDto.success(scheduleDto , "성공적으로 조회 하였습니다.");
    }

    @PatchMapping("{scheduleId}")
    public ResponseDto<ResponseScheduleDto> updateSchedule(@PathVariable Long scheduleId, @RequestBody RequestScheduleUpdateDto requestScheduleUpdateDto) {
        ResponseScheduleDto scheduleDto = scheduleService.updateScheduleById(scheduleId , requestScheduleUpdateDto);

        return ResponseDto.success(scheduleDto , "성공적으로 수정 하였습니다.");
    }

    @DeleteMapping("{scheduleId}")
    public ResponseDto<Long> deleteSchedule(@PathVariable Long scheduleId) {
        Long deletedId = scheduleService.deleteSchedule(scheduleId);
        return ResponseDto.success(deletedId , "성공적으로 삭제 하였습니다.");
    }
}
