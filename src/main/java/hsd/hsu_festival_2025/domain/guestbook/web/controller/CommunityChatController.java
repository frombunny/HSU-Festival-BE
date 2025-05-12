package hsd.hsu_festival_2025.domain.guestbook.web.controller;

import hsd.hsu_festival_2025.domain.community.exception.BadWordException;
import hsd.hsu_festival_2025.domain.community.service.CommunityMessageService;
import hsd.hsu_festival_2025.domain.guestbook.web.dto.GuestBookMessageReq;
import hsd.hsu_festival_2025.domain.guestbook.web.dto.GuestBookRes;
import hsd.hsu_festival_2025.global.socket.ConnectedUserTracker;
import hsd.hsu_festival_2025.global.validator.BadWordValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;


@Controller
@RequiredArgsConstructor
public class CommunityChatController {
    private final CommunityMessageService communityMessageService;
    private final ConnectedUserTracker connectedUserTracker;
    private final SimpMessagingTemplate messagingTemplate;
    private final BadWordValidator badWordValidator;

    @MessageMapping("/chat.send") // 클랑이언트 : /pub/chat.send
    public void sendMessage(GuestBookMessageReq req, Principal principal){

        String senderId = principal.getName();

        if(badWordValidator.containsBadWord(req.content())) throw new BadWordException();

        communityMessageService.sendChat(senderId, req);

        messagingTemplate.convertAndSend("/sub/chat/public", new GuestBookRes(
                req.username(), req.content(), senderId));
    }
}
