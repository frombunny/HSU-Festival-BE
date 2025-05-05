package hsd.hsu_festival_2025.global.socket;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@Component
public class SocketInterceptor implements HandshakeInterceptor {
    // WebSocket HandShake 전에 실행됨
    @Override
    public boolean beforeHandshake(ServerHttpRequest request,
                                   ServerHttpResponse response,
                                   WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws Exception {
        // Servlet 요청으로 캐스팅
        if(request instanceof ServletServerHttpRequest servletServerHttpRequest){
            HttpServletRequest httpServletRequest = servletServerHttpRequest.getServletRequest();
            Cookie[] cookies = httpServletRequest.getCookies();

            if(cookies != null){
                for(Cookie cookie : cookies){
                    if(cookie.getName().equals("user_id")){
                        // WebSocket 세션 attribute에 user_id 저장
                        attributes.put("user_id", cookie.getValue());
                        break;
                    }
                }

            }
        }
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
        System.out.println("WebSocket 연결 성공");

    }
}
