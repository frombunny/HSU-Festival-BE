package hsd.hsu_festival_2025.domain.community.web.dto;

public record CommunityMessageRes(
        String username,
        String content,
        String senderId, // 발신자 userid
        String time

) {
}
