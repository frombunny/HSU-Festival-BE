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

    @Column(name = "boothNum", nullable = false)
    private String boothNum;

    @Enumerated(EnumType.STRING)
    @Column (name = "type", nullable = false)
    private BoothType type;

    @Column (name = "boothName", nullable = false)
    private String name;

    @Column (name = "description", nullable = false)
    private String description;

    @Column (name = "time", nullable = false)
    private String time;

    @Column (name = "image_url")
    private String imageUrl;

    public void updateBooth(UpdateBoothReq updateBoothReq, String fileUrl) {
        this.type = updateBoothReq.type();
        this.boothNum = updateBoothReq.boothNum();
        this.name = updateBoothReq.name();
        this.description = updateBoothReq.description();
        this.time = updateBoothReq.time();
        this.imageUrl = fileUrl;
    }

    public static Booth toEntity(SaveBoothReq saveBoothReq, String fileUrl) {
        return Booth.builder()
                .type(saveBoothReq.type())
                .boothNum(saveBoothReq.boothNum())
                .name(saveBoothReq.name())
                .description(saveBoothReq.description())
                .time(saveBoothReq.time())
                .imageUrl(fileUrl)
                .build();
    }
}
