package sparta.advancedscheduler.auth.repository;

import org.springframework.data.repository.CrudRepository;
import sparta.advancedscheduler.auth.entity.UserAuthSession;

public interface UserAuthSessionRepository extends CrudRepository<UserAuthSession, Long> {
}
