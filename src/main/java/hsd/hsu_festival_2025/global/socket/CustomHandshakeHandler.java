package hsd.hsu_festival_2025.global.socket;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

public class CustomHandshakeHandler extends DefaultHandshakeHandler {
    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        String userId = (String) attributes.get("user_id");
        System.out.println("🧩 HandshakeHandler: user_id = " + userId);
        if (userId != null && !userId.isBlank()) {
            return () -> userId;
        }
        System.out.println("❌ Principal 생성 실패. user_id 없음.");
        return null;
    }

}
