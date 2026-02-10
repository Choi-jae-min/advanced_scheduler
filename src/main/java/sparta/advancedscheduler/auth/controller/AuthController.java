package sparta.advancedscheduler.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sparta.advancedscheduler.auth.dto.RequestLoginDto;
import sparta.advancedscheduler.auth.service.AuthService;

import java.time.Duration;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/sessions")
    public ResponseEntity<Void> login(
            @RequestBody RequestLoginDto requestLoginDto) {
        String sessionId = authService.login(requestLoginDto);

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
}
