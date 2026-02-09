package sparta.advancedscheduler.schedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sparta.advancedscheduler.schedule.entity.Schedule;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByPoster(String poster);
}
