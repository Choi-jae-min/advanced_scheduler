package sparta.advancedscheduler.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sparta.advancedscheduler.auth.entity.UserAuthSession;
import sparta.advancedscheduler.auth.repository.UserAuthSessionRepository;
import sparta.advancedscheduler.global.exception.auth.UnauthorizedException;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorizationService {

    private final UserAuthSessionRepository sessionRepository;

    public Long validateSession(String sessionId) {
        if(sessionId == null || sessionId.isEmpty()) {
            throw new UnauthorizedException();
        }

        UserAuthSession session = sessionRepository.findBySessionId(sessionId).orElseThrow(
                UnauthorizedException::new
        );
        if(session.isExpired()) {
            throw new UnauthorizedException();
        }

        return session.getUserId();
    }

    public void checkAuthorization(Long userId, Long userSessionId) {
        if(!userId.equals(userSessionId)){
            throw new UnauthorizedException("권한이 없습니다.");
        }
    }
}
