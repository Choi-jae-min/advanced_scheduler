package sparta.advancedscheduler.auth.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import sparta.advancedscheduler.auth.entity.UserAuthSession;

import java.util.Optional;


public interface UserAuthSessionRepository extends CrudRepository<UserAuthSession, Long> {
    @Query("select u from UserAuthSession u where u.sessionId = :sessionId and u.isActive = true")
    Optional<UserAuthSession> findBySessionId(@Param("sessionId") String sessionId);

    @Query("select u from UserAuthSession u where u.userId = :userId and u.isActive = true")
    Optional<UserAuthSession> findActiveUserByUserId(@Param("userId") Long userId);
}
