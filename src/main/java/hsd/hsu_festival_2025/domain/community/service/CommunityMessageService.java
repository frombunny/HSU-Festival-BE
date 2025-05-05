package hsd.hsu_festival_2025.domain.community.service;

import hsd.hsu_festival_2025.domain.community.web.dto.CommunityMessageReq;
import hsd.hsu_festival_2025.domain.community.web.dto.CommunityMessageRes;

import java.util.List;

public interface CommunityMessageService {
    void sendChat(String userId, CommunityMessageReq req);
    List<CommunityMessageRes> getRecentMessages(String userId);
}
