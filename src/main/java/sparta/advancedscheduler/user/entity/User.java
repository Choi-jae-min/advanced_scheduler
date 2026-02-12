package sparta.advancedscheduler.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import sparta.advancedscheduler.global.entity.AuditableEntity;
import sparta.advancedscheduler.user.dto.ResponseUpdateUserDto;

@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@SQLDelete(sql = "UPDATE users SET deleted_date = now() WHERE id = ?")
@SQLRestriction("deleted_date IS NULL")
public class User extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public void update(ResponseUpdateUserDto requestDto) {
        this.username = requestDto.getUsername();
    }
}