package com.sunshine.provider.config;

import com.sunshine.provider.utils.HTTPUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        if (HTTPUtils.isAjaxRequest(request)) {
            // AJAX请求,使用response发送403
            System.out.println("拒绝访问");
            response.sendError(403);
        } else if (!response.isCommitted()) {
            // 非AJAX请求，跳转系统默认的403错误界面，在web.xml中配置
            System.out.println("拒绝访问");
            response.sendError(HttpServletResponse.SC_FORBIDDEN, accessDeniedException.getMessage());
        }

    }
}
