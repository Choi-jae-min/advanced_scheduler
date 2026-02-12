package sparta.advancedscheduler.comment.service;

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
public class CommentService implements CommentServiceImpl{

    private final CommentRepository commentRepository;
    private final CommentFindService commentFindService;
    private final UserService userService;
    private final ScheduleServiceImpl scheduleService;
    private final AuthorizationService authorizationService;


    @Transactional
    public Long addComment(RequestCommentDto requestCommentDto, String sessionId) {
        Long userId = authorizationService.validateSession(sessionId);

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
    public ResponseCommentUpdateDto update(RequestCommentUpdateDto requestCommentUpdateDto,Long commentId, String sessionId) {
        Long userSessionId = authorizationService.validateSession(sessionId);

        Comment comment = commentFindService.getCommentById(commentId);
        authorizationService.checkAuthorization(userSessionId , comment.getUser().getId());
        comment.update(requestCommentUpdateDto.getComment());
        return new ResponseCommentUpdateDto(
                comment.getContent()
        );
    }

    @Transactional
    public void deleteComment(Long commentId, String sessionId) {
        Long userSessionId = authorizationService.validateSession(sessionId);

        Comment comment = commentFindService.getCommentById(commentId);

        authorizationService.checkAuthorization(userSessionId , comment.getUser().getId());

        commentRepository.delete(comment);
    }

}
