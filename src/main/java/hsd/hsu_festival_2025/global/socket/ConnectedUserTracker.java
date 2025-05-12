package hsd.hsu_festival_2025.global.socket;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class ConnectedUserTracker  {
    // 사용자 연결 관리 (세션 id와 userId를 매핑)
    private final ConcurrentHashMap<String,String> sessionIdToUserId = new ConcurrentHashMap<>();

    // 중복 없는 전체 접속자 목록 (현재 접속중인 userId)
    @Getter
    private final Set<String> connectedUserIds = ConcurrentHashMap.newKeySet();

    @EventListener
    public void handleConnect(SessionConnectedEvent event) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        String sessionId = accessor.getSessionId();

        if (accessor.getUser() == null) {
            log.warn("❌ Principal이 null입니다.");
            return;
        }

        String userId = accessor.getUser().getName(); // CustomHandshakeHandler에서 생성한 Principal 기반
         sessionIdToUserId.put(sessionId, userId);
         connectedUserIds.add(userId);
         log.info("✅ WebSocket 연결: session={}, userId={}", sessionId, userId);
}


    @EventListener
    public void handleDisconnect(SessionDisconnectEvent event) { // 연결 종료 시
        String sessionId = StompHeaderAccessor.wrap(event.getMessage()).getSessionId();
        String userId = sessionIdToUserId.remove(sessionId);
        if (userId != null) {
            connectedUserIds.remove(userId);
            log.info("WebSocket 연결 해제: session={}, userId={}", sessionId, userId);
        }
    }

}
