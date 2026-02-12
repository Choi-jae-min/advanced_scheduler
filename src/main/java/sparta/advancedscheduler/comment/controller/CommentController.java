package sparta.advancedscheduler.comment.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sparta.advancedscheduler.comment.dto.RequestCommentDto;
import sparta.advancedscheduler.comment.dto.RequestCommentUpdateDto;
import sparta.advancedscheduler.comment.dto.ResponseCommentDto;
import sparta.advancedscheduler.comment.dto.ResponseCommentUpdateDto;
import sparta.advancedscheduler.comment.service.CommentServiceImpl;
import sparta.advancedscheduler.global.dto.ResponseDto;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentServiceImpl commentService;

    @PostMapping
    public ResponseDto<Long> addComment(
            @Valid @RequestBody RequestCommentDto requestCommentDto,
            @CookieValue(name ="SESSION") String sessionId
    ) {
        Long commentId = commentService.addComment(requestCommentDto, sessionId);
        return ResponseDto.success(commentId , "댓글을 성공적으로 등록 하였습니다.");
    }

    @GetMapping("{scheduleId}")
    public ResponseDto<List<ResponseCommentDto>> getCommentById(@PathVariable Long scheduleId) {
        List<ResponseCommentDto> responseCommentDtoList = commentService.getAllCommentByScheduleId(scheduleId);

        return ResponseDto.success(responseCommentDtoList, "성공적으로 조회하였습니다.");
    }

    @PatchMapping("{commentId}")
    public ResponseDto<ResponseCommentUpdateDto> updateComment(
            @PathVariable Long commentId,
            @RequestBody RequestCommentUpdateDto requestCommentUpdateDto,
            @CookieValue(name = "SESSION") String sessionId) {
        ResponseCommentUpdateDto responseCommentUpdateDto = commentService.update(requestCommentUpdateDto,commentId,sessionId);

        return ResponseDto.success(responseCommentUpdateDto, "성공적으로 수정 되었습니다.");
    }

    @DeleteMapping("{commentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long commentId,
            @CookieValue(name = "SESSION") String sessionId
    ) {
        commentService.deleteComment(commentId , sessionId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
