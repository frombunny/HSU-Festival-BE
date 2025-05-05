package hsd.hsu_festival_2025.global.config.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
public class UserIdFilter implements Filter { // 서블릿 요청을 가로챔
    // 추적용 쿠키 이름 고정값 -> 쿠키를 찾거나 새로 만들 때 모두 이 이름 사용
    private static final String COOKIE_NAME = "user_id";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if(request instanceof HttpServletRequest && response instanceof HttpServletResponse){
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;

            Cookie[] cookies = req.getCookies();
            boolean found = false;

            if(cookies != null){
                for(Cookie cookie : cookies){
                    if(cookie.getName().equals(COOKIE_NAME)){ // 현재 요청에 user_id라는 쿠키가 있는지 확인
                        found = true;
                        break;
                    }
                }
            }

            if(!found){ // 쿠키가 없으면 새로 발급
                String uuid = UUID.randomUUID().toString();
                Cookie newCookie = new Cookie(COOKIE_NAME, uuid);
                newCookie.setPath("/"); // 전체 경로에서 사용
                newCookie.setMaxAge(60*60*24*30); // 한달
                res.addCookie(newCookie); // 응답에 쿠키 추가 -> 클라이언트가 저장
            }
        }

        chain.doFilter(request, response); // 다음 필터나 컨트롤러로 요청 넘김

    }


}
