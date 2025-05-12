package hsd.hsu_festival_2025.domain.community.web.controller;

import hsd.hsu_festival_2025.domain.community.service.CommunityMessageService;
import hsd.hsu_festival_2025.domain.community.web.dto.CommunityMessageRes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/community/chat")
public class CommunityChatQueryController {
    private final CommunityMessageService communityMessageService;

    @GetMapping("/messages")
    public List<CommunityMessageRes> getMessages(
            @CookieValue(value = "user_id", required = false) String userIdFromCookie,
            @RequestParam(value = "user_id", required = false) String userIdFromParam
    ) {
        String userId = userIdFromCookie != null ? userIdFromCookie : userIdFromParam;
        if (userId == null) return List.of();

        return communityMessageService.getRecentMessages(userId);
    }

}
