package sparta.advancedscheduler.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sparta.advancedscheduler.global.dto.ResponseDto;
import sparta.advancedscheduler.user.dto.ResponseUpdateUserDto;
import sparta.advancedscheduler.user.dto.ResponseUserDto;
import sparta.advancedscheduler.user.dto.ResponseUserListDto;
import sparta.advancedscheduler.user.dto.RequestUserDto;
import sparta.advancedscheduler.user.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseDto<Long> signUp(@RequestBody @Valid RequestUserDto requestDto) {
        Long userId = userService.createUser(requestDto);

        return ResponseDto.success(userId , "회원가입에 성공하였습니다.");
    }

    @GetMapping
    public ResponseDto<List<ResponseUserListDto>> getAllUsers(@RequestParam(required = false) String userName){
        List<ResponseUserListDto> userDto = userService.getUsers(userName);

        return ResponseDto.success(userDto , "유저 조회에 성공하였습니다.");
    }

    @GetMapping("{userId}")
    public ResponseDto<ResponseUserDto> getUser(@PathVariable Long userId) {
        ResponseUserDto userDto = userService.getUserByUserId(userId);

        return ResponseDto.success(userDto , "유저 조회에 성공하였습니다.");
    }

    @PatchMapping("{userId}")
    public ResponseDto<ResponseUpdateUserDto> updateUser(@PathVariable Long userId,@RequestBody ResponseUpdateUserDto requestDto) {
        ResponseUpdateUserDto updateUserDto = userService.updateUser(userId ,requestDto);

        return ResponseDto.success(updateUserDto , "유저 업데이트 성공 하였습니다.");
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
