package sparta.advancedscheduler.schedule.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.advancedscheduler.schedule.dto.RequestScheduleDto;
import sparta.advancedscheduler.schedule.dto.RequestScheduleUpdateDto;
import sparta.advancedscheduler.schedule.dto.ResponseScheduleDto;
import sparta.advancedscheduler.schedule.dto.ResponseScheduleListDto;
import sparta.advancedscheduler.schedule.entity.Schedule;
import sparta.advancedscheduler.schedule.repository.ScheduleRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public Long createSchedule(RequestScheduleDto requestScheduleDto) {
        Schedule schedule = new Schedule(
                requestScheduleDto.getTitle(),
                requestScheduleDto.getContent(),
                requestScheduleDto.getPoster()
        );
        scheduleRepository.save(schedule);

        return schedule.getId();
    }


    @Transactional(readOnly = true)
    public List<ResponseScheduleListDto> findScheduleListByName(String poster) {
        List<Schedule> schedules = scheduleRepository.findAllByPoster(poster);

        List<ResponseScheduleListDto> responseScheduleListDtos = new ArrayList<>();
        for (Schedule schedule : schedules) {
            ResponseScheduleListDto dto = new ResponseScheduleListDto(
                    schedule.getTitle()
            );

            responseScheduleListDtos.add(dto);
        }

        return responseScheduleListDtos;
    }

    @Transactional(readOnly = true)
    public ResponseScheduleDto findScheduleListById(Long scheduleId) {
        Schedule schedule = findScheduleById(scheduleId);

        return new ResponseScheduleDto(
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getPoster(),
                schedule.getCreatedDate(),
                schedule.getLastModifiedDate()
        );
    }

    @Transactional
    public ResponseScheduleDto updateScheduleById(Long scheduleId , RequestScheduleUpdateDto body) {
        Schedule schedule = findScheduleById(scheduleId);

        schedule.update(body);
        return new ResponseScheduleDto(
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getPoster(),
                schedule.getCreatedDate(),
                schedule.getLastModifiedDate()
        );
    }
    @Transactional
    public Long deleteSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new EntityNotFoundException("일정이 존재하지 않습니다 : " + scheduleId)
        );

        scheduleRepository.delete(schedule);
        return scheduleId;
    }

    @Transactional
    public Schedule findScheduleById(Long scheduleId) {
        return scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new EntityNotFoundException("일정이 존재하지 않습니다 : " + scheduleId)
        );
    }
}
