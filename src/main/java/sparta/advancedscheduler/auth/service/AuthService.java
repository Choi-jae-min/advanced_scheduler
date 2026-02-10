package sparta.advancedscheduler.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.advancedscheduler.auth.dto.RequestLoginDto;
import sparta.advancedscheduler.auth.entity.UserAuthSession;
import sparta.advancedscheduler.auth.repository.UserAuthSessionRepository;
import sparta.advancedscheduler.global.exception.auth.InvalidEmailException;
import sparta.advancedscheduler.global.exception.auth.InvalidPasswordException;
import sparta.advancedscheduler.user.entity.User;
import sparta.advancedscheduler.user.service.UserService;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserAuthSessionRepository sessionRepository;
    private final UserService userService;


    @Transactional
    public String login(RequestLoginDto requestLoginDto) {
        Optional<User> user = userService.getUser(requestLoginDto.getEmail());
        if (user.isEmpty()) {
            throw new InvalidEmailException();
        }

        if(!requestLoginDto.getPassword().equals(user.get().getPassword())) {
            throw new InvalidPasswordException();
        }

        String sessionId = UUID.randomUUID().toString();
        LocalDateTime expiresAt = LocalDateTime.now().plusDays(7);

        sessionRepository.save(new UserAuthSession(sessionId, user.get().getId(), expiresAt));

        return sessionId;
    }
}
