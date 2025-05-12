package hsd.hsu_festival_2025.domain.community.web.controller;

import hsd.hsu_festival_2025.domain.community.exception.BadWordException;
import hsd.hsu_festival_2025.domain.community.service.CommunityMessageService;
import hsd.hsu_festival_2025.domain.community.web.dto.CommunityMessageReq;
import hsd.hsu_festival_2025.domain.community.web.dto.CommunityMessageRes;
import hsd.hsu_festival_2025.global.socket.ConnectedUserTracker;
import hsd.hsu_festival_2025.global.validator.BadWordValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.time.LocalTime.now;


@Controller
@RequiredArgsConstructor
public class CommunityChatController {
    private final CommunityMessageService communityMessageService;
    private final ConnectedUserTracker connectedUserTracker;
    private final SimpMessagingTemplate messagingTemplate;
    private final BadWordValidator badWordValidator;

    @MessageMapping("/chat.send") // 클랑이언트 : /pub/chat.send
    public void sendMessage(CommunityMessageReq req, Principal principal){
        String senderId = (principal != null) ? principal.getName() : req.userId();

        if (senderId == null || senderId.isBlank()) {
            System.out.println("❌ senderId 없음 → 메시지 무시");
            return;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        System.out.println("📦 userId in payload: " + req.userId());

        if(badWordValidator.containsBadWord(req.content())) throw new BadWordException();

        communityMessageService.sendChat(senderId, req);

        messagingTemplate.convertAndSend("/sub/chat/public", new CommunityMessageRes(
                req.username(), req.content(), senderId, LocalDateTime.now().format(formatter)));
    }
}
