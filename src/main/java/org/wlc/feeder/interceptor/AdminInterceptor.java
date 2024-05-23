package org.wlc.feeder.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.wlc.feeder.annotation.AdminAuth;

import java.lang.reflect.Method;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/5/22 上午11:09
 */
public class AdminInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(AdminInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Method method = ((HandlerMethod) handler).getMethod();

        if (method.isAnnotationPresent(AdminAuth.class) && !request.getRemoteHost().equals("127.0.0.1")) {
            log.info("ip want {} visit adminApi", request.getRemoteHost());
            response.setStatus(401);
            response.getWriter().write("no auth for adminApi");
            return false;
        }
        return true;
    }
}
