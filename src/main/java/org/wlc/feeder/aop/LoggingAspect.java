package org.wlc.feeder.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.wlc.feeder.exception.BizException;

/**
 * //TODO add class commment here
 *
 * @Author wfh
 * @Date 2024/4/21 上午11:31
 */
@Aspect
@Component
@Slf4j
public class LoggingAspect {
    @Around("execution(public org.springframework.http.ResponseEntity org.wlc.feeder.controller.*.*(..))")
    public ResponseEntity aroundPublicMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        // 执行目标方法
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (BizException e) {
            log.error(getSignature(joinPoint) + "BizException: {}", e.getMessage(),e);
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            log.error(getSignature(joinPoint) + "Exception: {}", e.getMessage(),e);
            return ResponseEntity.internalServerError().body("服务器内部错误请联系 wangfeihong274@gmail.com");
        }
        return (ResponseEntity) result;
    }

    private String getSignature(ProceedingJoinPoint joinPoint) {
        StringBuilder sb = new StringBuilder();
        sb.append(joinPoint.getClass().toString());
        sb.append(" ");
        sb.append(joinPoint.getSignature());
        sb.append(" ");
        sb.append(joinPoint.getArgs());
        return sb.toString();
    }
}
