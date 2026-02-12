package sparta.advancedscheduler.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sparta.advancedscheduler.global.dto.ResponseDto;
import sparta.advancedscheduler.user.dto.ResponseUpdateUserDto;
import sparta.advancedscheduler.user.dto.ResponseUserDto;
import sparta.advancedscheduler.user.dto.ResponseUserListDto;
import sparta.advancedscheduler.user.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseDto<List<ResponseUserListDto>> getAllUsers(@RequestParam(required = false) String userName){
        List<ResponseUserListDto> userDto = userService.getUsers(userName);

        return ResponseDto.success(userDto , "유저 조회에 성공하였습니다.");
    }

    @GetMapping("{userId}")
    public ResponseDto<ResponseUserDto> getUser(@PathVariable Long userId) {
        ResponseUserDto userDto = userService.getUserDtoByUserId(userId);

        return ResponseDto.success(userDto , "유저 조회에 성공하였습니다.");
    }

    @PatchMapping("")
    public ResponseDto<ResponseUpdateUserDto> updateUser(
            @CookieValue(name = "SESSION", required = false) String sessionId,
            @RequestBody ResponseUpdateUserDto requestDto) {
        ResponseUpdateUserDto updateUserDto = userService.updateUser(sessionId ,requestDto);
        return ResponseDto.success(updateUserDto , "유저 업데이트 성공 하였습니다.");
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteUser(
            @CookieValue(name = "SESSION", required = false) String sessionId) {
        userService.deleteUser(sessionId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
