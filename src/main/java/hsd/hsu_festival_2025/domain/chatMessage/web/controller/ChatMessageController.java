package hsd.hsu_festival_2025.domain.chatMessage.web.controller;

import hsd.hsu_festival_2025.domain.chatMessage.service.ChatMessageService;
import hsd.hsu_festival_2025.domain.chatMessage.web.dto.SendMessageReq;
import hsd.hsu_festival_2025.domain.chatMessage.web.dto.SendMessageRes;
import hsd.hsu_festival_2025.global.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chatMessage")
@RequiredArgsConstructor
public class ChatMessageController {
    private final ChatMessageService chatMessageService;
    // 메세지 전송
    @PostMapping
    public SuccessResponse<?> sendMessage(@RequestBody SendMessageReq sendMessageReq) {
        SendMessageRes sendMessageRes = chatMessageService.addNewChatMessage(sendMessageReq);
        return SuccessResponse.created(sendMessageRes);
    }
}
