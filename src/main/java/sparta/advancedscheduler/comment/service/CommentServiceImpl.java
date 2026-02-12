package sparta.advancedscheduler.comment.service;

import sparta.advancedscheduler.comment.dto.RequestCommentDto;
import sparta.advancedscheduler.comment.dto.RequestCommentUpdateDto;
import sparta.advancedscheduler.comment.dto.ResponseCommentDto;
import sparta.advancedscheduler.comment.dto.ResponseCommentUpdateDto;

import java.util.List;

public interface CommentServiceImpl {
    Long addComment(RequestCommentDto requestCommentDto, String sessionId);
    List<ResponseCommentDto> getAllCommentByScheduleId(Long scheduleId);
    ResponseCommentUpdateDto update(RequestCommentUpdateDto requestCommentUpdateDto, Long commentId, String sessionId);
    void deleteComment(Long commentId, String sessionId);
}
