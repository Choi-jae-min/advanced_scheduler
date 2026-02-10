package sparta.advancedscheduler.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sparta.advancedscheduler.auth.dto.RequestLoginDto;
import sparta.advancedscheduler.auth.dto.ResponseLoginDto;
import sparta.advancedscheduler.auth.service.AuthService;
import sparta.advancedscheduler.global.dto.ResponseDto;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/sessions")
    public ResponseDto<ResponseLoginDto> login(@RequestBody RequestLoginDto requestLoginDto) {
        String session = authService.login(requestLoginDto);

        ResponseLoginDto responseLoginDto = new ResponseLoginDto(session);
        return ResponseDto.success(responseLoginDto , "로그인 성공");
    }
}
