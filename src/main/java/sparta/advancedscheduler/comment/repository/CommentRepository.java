package sparta.advancedscheduler.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sparta.advancedscheduler.comment.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByScheduleId(Long scheduleId);
}
