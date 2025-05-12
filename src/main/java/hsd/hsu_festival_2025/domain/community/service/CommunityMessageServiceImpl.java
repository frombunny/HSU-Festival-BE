package hsd.hsu_festival_2025.domain.community.service;

import hsd.hsu_festival_2025.domain.community.entity.CommunityMessage;
import hsd.hsu_festival_2025.domain.community.exception.BadWordException;
import hsd.hsu_festival_2025.domain.community.exception.CommunityErrorCode;
import hsd.hsu_festival_2025.domain.community.repository.CommunityChatRepository;
import hsd.hsu_festival_2025.domain.community.web.dto.CommunityMessageReq;
import hsd.hsu_festival_2025.domain.community.web.dto.CommunityMessageRes;
import hsd.hsu_festival_2025.global.validator.BadWordValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CommunityMessageServiceImpl implements CommunityMessageService {
    private final CommunityChatRepository communityChatRepository;
    private final BadWordValidator badWordValidator;

    @Override
    public void sendChat(String userId, CommunityMessageReq req) {
        System.out.println("✅ DB 저장 시도 - userId = " + userId + ", content = " + req.content());

        CommunityMessage message = CommunityMessage.toEntity(req, userId);

        communityChatRepository.save(message);
    }

    @Override
    public List<CommunityMessageRes> getRecentMessages(String userId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return communityChatRepository.findTop100ByOrderByCreatedAtDesc().stream()
                .map(msg -> new CommunityMessageRes(
                        msg.getUsername(),
                        msg.getContent(),
                        msg.getUserId(),
                        msg.getCreatedAt().format(formatter)
                ))
                .toList();
    }


}
