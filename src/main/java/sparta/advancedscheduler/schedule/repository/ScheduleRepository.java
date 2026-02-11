package sparta.advancedscheduler.schedule.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sparta.advancedscheduler.schedule.entity.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("""
    SELECT s
    FROM Schedule s
    WHERE (:userId IS NULL OR s.user.id = :userId)
    ORDER BY s.lastModifiedDate DESC
    """)
    Page<Schedule> findAllByUserIdOrderByLastModifiedDateDesc(Long userId,Pageable pageable);
}
