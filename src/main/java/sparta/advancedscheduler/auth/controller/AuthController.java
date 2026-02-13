package sparta.advancedscheduler.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sparta.advancedscheduler.auth.dto.RequestLoginDto;
import sparta.advancedscheduler.auth.service.AuthenticationService;
import sparta.advancedscheduler.global.dto.ResponseDto;
import sparta.advancedscheduler.user.dto.RequestUserDto;
import sparta.advancedscheduler.user.service.UserService;

import java.time.Duration;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;
    private final UserService userService;
    @PostMapping("/sessions")
    public ResponseEntity<Void> login(
            @RequestBody RequestLoginDto requestLoginDto) {
        String sessionId = authenticationService.login(requestLoginDto);

        ResponseCookie cookie = ResponseCookie.from("SESSION", sessionId)
                .httpOnly(true)
                .secure(true)
                .sameSite("Lax")
                .path("/")
                .maxAge(Duration.ofDays(7))
                .build();

        return ResponseEntity
                .noContent()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .build();
    }

    @PostMapping
    public ResponseEntity<ResponseDto<Long>> signUp(@RequestBody @Valid RequestUserDto requestDto) {
        Long userId = userService.createUser(requestDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseDto.success(userId , "회원가입에 성공하였습니다."));
    }
}
