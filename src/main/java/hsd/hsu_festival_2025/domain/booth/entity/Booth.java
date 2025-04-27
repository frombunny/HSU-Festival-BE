package hsd.hsu_festival_2025.domain.booth.entity;

import hsd.hsu_festival_2025.domain.booth.entity.enums.BoothType;
import hsd.hsu_festival_2025.domain.booth.web.dto.SaveBoothReq;
import hsd.hsu_festival_2025.domain.booth.web.dto.UpdateBoothReq;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor (access = AccessLevel.PROTECTED)
@NoArgsConstructor (access = AccessLevel.PROTECTED)
@Table(name = "booth")
public class Booth {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column (name = "type", nullable = false)
    private BoothType type;

    @Column (name = "boothName", nullable = false)
    private String name;

    @Column (name = "description", nullable = false)
    private String description;

    @Column (name = "time", nullable = false)
    private String time;

    public void updateBooth(UpdateBoothReq updateBoothReq) {
        this.type = updateBoothReq.type();
        this.name = updateBoothReq.name();
        this.description = updateBoothReq.description();
        this.time = updateBoothReq.time();
    }

    public static Booth toEntity(SaveBoothReq saveBoothReq) {
        return Booth.builder()
                .type(saveBoothReq.type())
                .name(saveBoothReq.name())
                .description(saveBoothReq.description())
                .time(saveBoothReq.time())
                .build();
    }
}
