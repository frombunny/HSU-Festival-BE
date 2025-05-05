package hsd.hsu_festival_2025.global.socket;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

public class CustomHandshakeHandler extends DefaultHandshakeHandler {
    @Override
    protected Principal determineUser(
            ServerHttpRequest request,
            WebSocketHandler wsHandler,
            Map<String, Object> attributes) {

        String userId = (String) attributes.get("user_id");

        if (userId != null && !userId.isBlank()) {
            return () -> userId;
        }

        // 익명 사용자 or 인증 실패 처리 (예: null 리턴 → WebSocket 거부)
        return null;
    }
}
