package sparta.advancedscheduler.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.advancedscheduler.auth.dto.RequestLoginDto;
import sparta.advancedscheduler.auth.entity.UserAuthSession;
import sparta.advancedscheduler.auth.repository.UserAuthSessionRepository;
import sparta.advancedscheduler.global.exception.auth.InvalidEmailException;
import sparta.advancedscheduler.global.exception.auth.InvalidPasswordException;
import sparta.advancedscheduler.global.utility.PasswordEncoder;
import sparta.advancedscheduler.user.entity.User;
import sparta.advancedscheduler.user.service.UserService;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserAuthSessionRepository sessionRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public String login(RequestLoginDto requestLoginDto) {
        Optional<User> user = userService.getUser(requestLoginDto.getEmail());
        if (user.isEmpty()) {
            throw new InvalidEmailException();
        }
        checkUserAuthSessionIsActive(user.get().getId());
        if(!passwordEncoder.matches(requestLoginDto.getPassword(), user.get().getPassword())) {
            throw new InvalidPasswordException();
        }

        LocalDateTime expiresAt = LocalDateTime.now().plusDays(7);
        UserAuthSession session = sessionRepository.save(new UserAuthSession(user.get().getId(), expiresAt));

        return session.getSessionId();
    }

    private void checkUserAuthSessionIsActive(Long userId) {
        Optional<UserAuthSession> userAuthSession = sessionRepository.findActiveUserByUserId(userId);
        userAuthSession.ifPresent(UserAuthSession::expire);
    }

}
