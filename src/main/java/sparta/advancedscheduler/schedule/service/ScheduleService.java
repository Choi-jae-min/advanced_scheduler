package sparta.advancedscheduler.schedule.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.advancedscheduler.auth.service.AuthorizationService;
import sparta.advancedscheduler.comment.service.CommentService;
import sparta.advancedscheduler.schedule.dto.RequestScheduleDto;
import sparta.advancedscheduler.schedule.dto.RequestScheduleUpdateDto;
import sparta.advancedscheduler.schedule.dto.ResponseScheduleDto;
import sparta.advancedscheduler.schedule.dto.ResponseScheduleListDto;
import sparta.advancedscheduler.schedule.entity.Schedule;
import sparta.advancedscheduler.schedule.repository.ScheduleRepository;
import sparta.advancedscheduler.user.entity.User;
import sparta.advancedscheduler.user.service.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final AuthorizationService authorizationService;
    private final ScheduleFinder scheduleFinder;
    private final CommentService commentService;
    private final UserService userService;


    @Transactional
    public Long createSchedule(Long userId , RequestScheduleDto requestScheduleDto) {
        User user = userService.getUserById(userId);

        Schedule schedule = new Schedule(
                requestScheduleDto.getTitle(),
                requestScheduleDto.getContent(),
                user
        );
        scheduleRepository.save(schedule);

        return schedule.getId();
    }


    @Transactional(readOnly = true)
    public Page<ResponseScheduleListDto> findScheduleListByName(Long userID , Pageable pageable) {
        Page<Schedule> schedules = scheduleRepository.findAllByUserIdOrderByLastModifiedDateDesc(userID,pageable);

        List<ResponseScheduleListDto> dtoList = schedules.getContent().stream()
                .map(schedule -> new ResponseScheduleListDto(
                            schedule.getTitle(),
                            schedule.getContent(),
                            schedule.getUser().getUsername(),
                            commentService.getCommentCount(schedule.getId()),
                            schedule.getCreatedDate(),
                            schedule.getLastModifiedDate()
                    )
                )
                .toList();

        return new PageImpl<>(
                dtoList,
                pageable,
                schedules.getTotalElements()
        );
    }

    @Transactional(readOnly = true)
    public ResponseScheduleDto findScheduleListById(Long scheduleId) {
        Schedule schedule = scheduleFinder.findScheduleById(scheduleId);

        return new ResponseScheduleDto(
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getUser().getUsername(),
                schedule.getCreatedDate(),
                schedule.getLastModifiedDate()
        );
    }

    @Transactional
    public ResponseScheduleDto updateScheduleById(Long userId , Long scheduleId , RequestScheduleUpdateDto body) {
        Schedule schedule = scheduleFinder.findScheduleById(scheduleId);
        authorizationService.checkAuthorization(schedule.getUser().getId() ,userId);

        schedule.update(body);
        return new ResponseScheduleDto(
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getUser().getUsername(),
                schedule.getCreatedDate(),
                schedule.getLastModifiedDate()
        );
    }
    @Transactional
    public Long deleteSchedule(Long userId,Long scheduleId) {
        Schedule schedule = scheduleFinder.findScheduleById(scheduleId);
        authorizationService.checkAuthorization(schedule.getUser().getId() ,userId);
        scheduleRepository.delete(schedule);
        return scheduleId;
    }


}
