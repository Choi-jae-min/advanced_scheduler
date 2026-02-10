package sparta.advancedscheduler.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sparta.advancedscheduler.user.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u WHERE :username is null or u.username = :username")
    List<User> findALlByUsername(@Param("username") String username);
}
