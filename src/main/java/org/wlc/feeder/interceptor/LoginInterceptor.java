package org.wlc.feeder.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.wlc.feeder.util.JwtUtils;

import java.util.Objects;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/3/29 下午5:34
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    private static final String AUTHORIZATION_HEADER = "Authorization";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(AUTHORIZATION_HEADER);

        try {

            if (Objects.isNull(token) || Objects.isNull(JwtUtils.validateAndGetOpenId(token))) {
                response.setStatus(401);
                return false;
            }
        } catch (Exception e) {
            response.setStatus(401);
            return false;
        }


        return true;
    }
}
