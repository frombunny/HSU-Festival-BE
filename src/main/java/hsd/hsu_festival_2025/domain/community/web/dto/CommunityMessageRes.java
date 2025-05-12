package hsd.hsu_festival_2025.domain.community.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommunityMessageRes {
    private String username;
    private String content;
    private String senderId;
    private String time;
}

