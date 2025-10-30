package com.itmo.evaluationSystem.interceptor;

import com.itmo.evaluationSystem.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Service
public class TokenInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("token");
        if (token == null|| token.isEmpty()) {

            log.info("token is empty");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        try{
            JwtUtils.parseJwt(token);
        }catch (Exception e){
            log.info("token parse error");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        log.info("token parse success");


        return true;
    }
}
