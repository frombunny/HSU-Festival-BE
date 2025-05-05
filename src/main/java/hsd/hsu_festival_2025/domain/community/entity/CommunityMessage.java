package hsd.hsu_festival_2025.domain.community.entity;

import hsd.hsu_festival_2025.domain.booth.entity.Booth;
import hsd.hsu_festival_2025.domain.booth.web.dto.SaveBoothReq;
import hsd.hsu_festival_2025.domain.community.web.dto.CommunityMessageReq;
import hsd.hsu_festival_2025.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Community_Message")
public class CommunityMessage extends BaseEntity {
    @Id @GeneratedValue
    private Long id;

    @Column(name ="content")
    private String content;

    @Column(name ="username")
    private String username;

    // 쿠키 기반
    @Column(name = "userId")
    private String userId;


    public static CommunityMessage toEntity(CommunityMessageReq communityMessageReq, String userId) {
        return CommunityMessage.builder()
                .content(communityMessageReq.content())
                .username(communityMessageReq.username())
                .userId(userId)
                .build();

    }
}
