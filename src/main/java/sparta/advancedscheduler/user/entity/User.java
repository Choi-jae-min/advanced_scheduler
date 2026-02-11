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

// 유저를 지우면 -->> 유저가 사라져야한다. -->> 연관관계가 있어서 못지운다.
// 유저가 지워졌어요 ( 주민번호 비밀번호 실제로 지우고) ( 이름, 이메일 )
// 유저가 지워졌어요 (실제로 사라지진않음) -->> 일정 이랑 (하드 딜리트), 댓글(하드 딜리트)
