package hsd.hsu_festival_2025.domain.guestbook.entity;

import hsd.hsu_festival_2025.domain.community.entity.CommunityMessage;
import hsd.hsu_festival_2025.domain.community.web.dto.CommunityMessageReq;
import hsd.hsu_festival_2025.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Guestbook extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="content")
    private String content;

    @Column(name ="username")
    private String username;

    public static CommunityMessage toEntity(CommunityMessageReq communityMessageReq, String userId) {
        return CommunityMessage.builder()
                .content(communityMessageReq.content())
                .username(communityMessageReq.username())
                .userId(userId)
                .build();

    }
}
