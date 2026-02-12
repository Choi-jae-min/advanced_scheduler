package sparta.advancedscheduler.comment.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.advancedscheduler.comment.entity.Comment;
import sparta.advancedscheduler.comment.repository.CommentRepository;

@Service
@RequiredArgsConstructor
public class CommentFindService {
    private final CommentRepository commentRepository;

    @Transactional(readOnly = true)
    public long getCommentCount(Long scheduleId) {
        return  commentRepository.countByScheduleId(scheduleId);
    }

    @Transactional(readOnly = true)
    public Comment getCommentById(Long commentId) {
        return  commentRepository.findById(commentId).orElseThrow(() -> new EntityNotFoundException("댓글이 존재 하지 않습니다."));
    }
}
