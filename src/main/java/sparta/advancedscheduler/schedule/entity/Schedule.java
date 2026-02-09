package sparta.advancedscheduler.schedule.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sparta.advancedscheduler.global.entity.AuditableEntity;
import sparta.advancedscheduler.schedule.dto.RequestScheduleDto;
import sparta.advancedscheduler.schedule.dto.RequestScheduleUpdateDto;


@Getter
@Entity
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String poster;

    public Schedule(String title, String content, String poster) {
        this.title = title;
        this.content = content;
        this.poster = poster;
    }

    public void update(RequestScheduleUpdateDto dto) {
        if (dto.getTitle() != null) {
            this.title = dto.getTitle();
        }
        if (dto.getContent() != null) {
            this.content = dto.getContent();
        }
    }
}
