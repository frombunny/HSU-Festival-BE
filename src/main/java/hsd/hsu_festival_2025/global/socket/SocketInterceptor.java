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
//    @Override
//    public boolean beforeHandshake(ServerHttpRequest request,
//                                   ServerHttpResponse response,
//                                   WebSocketHandler wsHandler,
//                                   Map<String, Object> attributes) throws Exception {
//        // Servlet 요청으로 캐스팅
//        if (request instanceof ServletServerHttpRequest servletRequest) {
//            HttpServletRequest httpServletRequest = servletRequest.getServletRequest();
//            Cookie[] cookies = httpServletRequest.getCookies();
//
//            System.out.println("🔍 쿠키 확인:");
//            if (cookies != null) {
//                for (Cookie cookie : cookies) {
//                    System.out.println("- " + cookie.getName() + "=" + cookie.getValue());
//                    if ("user_id".equals(cookie.getName())) {
//                        attributes.put("user_id", cookie.getValue());
//                        System.out.println("✅ user_id 쿠키 세팅됨: " + cookie.getValue());
//                    }
//                }
//            } else {
//                System.out.println("❌ cookies == null");
//            }
//        }
//
//
//        return true;
//    }
    @Override
    public boolean beforeHandshake(ServerHttpRequest request,
                                   ServerHttpResponse response,
                                   WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) {
        if (request instanceof ServletServerHttpRequest servletRequest) {
            HttpServletRequest httpRequest = servletRequest.getServletRequest();

            // 1. 쿠키에서 user_id 찾기
            Cookie[] cookies = httpRequest.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("user_id".equals(cookie.getName())) {
                        attributes.put("user_id", cookie.getValue());
                        return true;
                    }
                }
            }

            // 2. 쿼리스트링에서 user_id 찾기 (fallback)
            String userIdParam = httpRequest.getParameter("user_id");
            if (userIdParam != null && !userIdParam.isBlank()) {
                attributes.put("user_id", userIdParam);
                return true;
            }

            System.out.println("❌ user_id 못 찾음 (쿠키/쿼리 모두 없음)");
        }

        return true;
    }


    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
        System.out.println("WebSocket 연결 성공");

    }
}
