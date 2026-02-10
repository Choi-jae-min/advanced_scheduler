package sparta.advancedscheduler.auth.repository;

import org.springframework.data.repository.CrudRepository;
import sparta.advancedscheduler.auth.entity.UserAuthSession;

import java.util.Optional;


public interface UserAuthSessionRepository extends CrudRepository<UserAuthSession, Long> {
    Optional<UserAuthSession> findBySessionId(String sessionId);
}
