package com.dztzb00t3.j2t.filter;

import com.dztzb003.j2t.common.utils.TokenUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * 过滤器,拦截器
 *
 * @author j2t
 * @date 2024/09/20 10:56
 */
@Slf4j
@Component
public class UserTokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        if (requestURI.contains("/user/login")) { // 放行
            filterChain.doFilter(request, response);
            return;
        }


        String authorization = request.getHeader("Authorization");
        if (StringUtils.isNotBlank(authorization)) {
            String username = TokenUtils.getUsernameFromToken(authorization);
            if (StringUtils.isBlank(username)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("登陆失效");
                return;
            }
            log.info("时间:{} ;登陆人: {};", LocalDateTime.now(), username);
            System.out.println(username);


            filterChain.doFilter(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("未登录，请重新登录");
        }
        return;
    }


}
