package sparta.advancedscheduler.auth.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_auth_sessions")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserAuthSession {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String sessionId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    @Column(nullable = false)
    private boolean isActive;

    public UserAuthSession(Long userId, LocalDateTime expiresAt) {
        this.userId = userId;
        this.expiresAt = expiresAt;
        this.isActive = true;
    }

    public boolean isExpired() {
        if(expiresAt.isBefore(LocalDateTime.now()) || !isActive){
            this.isActive = false;
            return true;
        }
        return false;
    }

    public void expire() {
        this.isActive = false;
    }
}
