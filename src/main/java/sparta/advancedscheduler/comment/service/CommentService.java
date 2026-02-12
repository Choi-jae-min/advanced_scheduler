package sparta.advancedscheduler.comment.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.advancedscheduler.auth.service.AuthorizationService;
import sparta.advancedscheduler.comment.dto.RequestCommentDto;
import sparta.advancedscheduler.comment.dto.RequestCommentUpdateDto;
import sparta.advancedscheduler.comment.dto.ResponseCommentDto;
import sparta.advancedscheduler.comment.dto.ResponseCommentUpdateDto;
import sparta.advancedscheduler.comment.entity.Comment;
import sparta.advancedscheduler.comment.repository.CommentRepository;
import sparta.advancedscheduler.schedule.entity.Schedule;
import sparta.advancedscheduler.schedule.service.ScheduleServiceImpl;
import sparta.advancedscheduler.user.entity.User;
import sparta.advancedscheduler.user.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final ScheduleServiceImpl scheduleService;
    private final AuthorizationService authorizationService;

    @Transactional
    public Long addComment(RequestCommentDto requestCommentDto, Long userId) {
        User user = userService.getUserById(userId);
        Schedule schedule = scheduleService.findScheduleById(requestCommentDto.getScheduleId());
        Comment comment = new Comment(requestCommentDto.getContent() ,user,schedule);

        commentRepository.save(comment);
        return comment.getId();
    }
    @Transactional(readOnly = true)
    public List<ResponseCommentDto> getAllCommentByScheduleId(Long scheduleId) {
        List<Comment> commentList = commentRepository.findAllByScheduleId(scheduleId);
        List<ResponseCommentDto> responseCommentDtoList = new ArrayList<>();

        commentList.forEach(comment -> responseCommentDtoList.add(
                new ResponseCommentDto(comment.getContent() , comment.getUser().getUsername())
        ));
        return responseCommentDtoList;
    }

    @Transactional
    public ResponseCommentUpdateDto update(RequestCommentUpdateDto requestCommentUpdateDto,Long commentId, Long userSessionId) {
        Comment comment = getCommentById(commentId);
        authorizationService.checkAuthorization(userSessionId , comment.getUser().getId());
        comment.update(requestCommentUpdateDto.getComment());
        return new ResponseCommentUpdateDto(
                comment.getContent()
        );
    }

    @Transactional
    public void deleteComment(Long commentId, Long userSessionId) {
        Comment comment = getCommentById(commentId);

        authorizationService.checkAuthorization(userSessionId , comment.getUser().getId());

        commentRepository.delete(comment);
    }
    @Transactional(readOnly = true)
    public long getCommentCount(Long scheduleId) {
        return  commentRepository.countByScheduleId(scheduleId);
    }

    @Transactional(readOnly = true)
    public Comment getCommentById(Long commentId) {
        return  commentRepository.findById(commentId).orElseThrow(() -> new EntityNotFoundException("댓글이 존재 하지 않습니다."));
    }

}
