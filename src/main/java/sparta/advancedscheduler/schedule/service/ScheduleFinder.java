package sparta.advancedscheduler.schedule.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.advancedscheduler.schedule.entity.Schedule;
import sparta.advancedscheduler.schedule.repository.ScheduleRepository;

@Service
@RequiredArgsConstructor
public class ScheduleFinder {
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public Schedule findScheduleById(Long scheduleId) {
        return scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new EntityNotFoundException("일정이 존재하지 않습니다 : " + scheduleId)
        );
    }
}
