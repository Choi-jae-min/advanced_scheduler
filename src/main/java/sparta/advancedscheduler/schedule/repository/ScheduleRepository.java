package sparta.advancedscheduler.schedule.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sparta.advancedscheduler.schedule.entity.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Page<Schedule> findAllByPosterOrderByLastModifiedDateDesc(String poster,Pageable pageable);
}
