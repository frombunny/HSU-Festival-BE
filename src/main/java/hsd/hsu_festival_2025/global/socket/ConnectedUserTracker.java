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
    public void handleConnect(SessionConnectedEvent event) { // 연결 발생 시
        // 세션 정보 접근
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());

        if (accessor.getSessionAttributes() == null) {
            log.warn("세션 attribute가 null임");
            return; // 이거 없으면 NullPointerException
        }

        String sessionId = accessor.getSessionId();
        // SocketInterceptor에서 저장한 세션 attribute
        String userId = (String) accessor.getSessionAttributes().get("user_id");

        if (userId != null) {
            sessionIdToUserId.put(sessionId, userId);
            connectedUserIds.add(userId); // 접속자 목록에 추가
            log.info("WebSocket 연결: session={}, userId={}", sessionId, userId);
        }
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
