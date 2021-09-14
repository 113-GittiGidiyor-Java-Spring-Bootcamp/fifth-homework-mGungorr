package dev.patika.homework.config;

import dev.patika.homework.util.ClientRequestInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ClientLogInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    ClientRequestInfo clientRequestInfo;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        clientRequestInfo.setClientURL(request.getRequestURI());
        clientRequestInfo.setClientIpAdress(request.getRemoteAddr());
        clientRequestInfo.setSessionActivityId(request.getSession().getId());
        return true;
    }

}
