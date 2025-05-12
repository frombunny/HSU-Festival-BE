package hsd.hsu_festival_2025.global.config;

import hsd.hsu_festival_2025.global.socket.CustomHandshakeHandler;
import hsd.hsu_festival_2025.global.socket.SocketInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

/**
 * STOMP 기반 WebSocket 메시징 설정
 */
@Configuration
@EnableWebSocketMessageBroker // 웹소켓 활성화
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    private final SocketInterceptor socketInterceptor;

    public WebSocketConfig(SocketInterceptor socketInterceptor) {
        this.socketInterceptor = socketInterceptor;
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        System.out.println("✅ registerStompEndpoints 실행됨");
        // /ws/community 경로로 들어오는 웹소켓 요청을 CommunityWebSocketHandler로 처리
        registry.addEndpoint("/ws/community")
                .setAllowedOriginPatterns(
                "http://localhost:*",
                "https://*.netlify.app"
                ) // 패턴 기반 origin 허용
                .setHandshakeHandler(new CustomHandshakeHandler())
                .addInterceptors(socketInterceptor)
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/sub", "/queue"); // 구독용 prefix
        registry.setApplicationDestinationPrefixes("/pub"); // 송신용 prefix
        registry.setUserDestinationPrefix("/user"); // 없으면 SenToUser 작동 X
    }
}
