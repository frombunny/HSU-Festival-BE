package hsd.hsu_festival_2025.domain.community.web.controller;

import hsd.hsu_festival_2025.domain.community.service.CommunityMessageService;
import hsd.hsu_festival_2025.domain.community.web.dto.CommunityMessageRes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/community/chat")
public class CommunityChatQueryController {
    private final CommunityMessageService communityMessageService;

    @GetMapping("/messages")
    public List<CommunityMessageRes> getMessages(@CookieValue(value = "user_id", required = false) String userId) {
        if (userId == null) {
            return List.of();
        }
        return communityMessageService.getRecentMessages(userId);
    }
}
