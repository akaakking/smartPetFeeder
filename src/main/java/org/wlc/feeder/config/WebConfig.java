package org.wlc.feeder.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
import org.wlc.feeder.interceptor.AdminInterceptor;
import org.wlc.feeder.interceptor.LoginInterceptor;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/3/29 下午5:47
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AdminInterceptor()).addPathPatterns("/api/admin/**");

        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/user/login/**")
                .excludePathPatterns("/api/image/**")
                .excludePathPatterns("/api/blog/all")
                .excludePathPatterns("/api/blog/detail")
                .excludePathPatterns("/api/admin/**");
    }
}
