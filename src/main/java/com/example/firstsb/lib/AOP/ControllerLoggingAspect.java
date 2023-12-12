package com.example.firstsb.lib.AOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(ControllerLoggingAspect.class);

    @Around("execution(* com.example.firstsb.controller.*.*(..))")
    public Object logControllerAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        try {
            // 记录方法开始执行
            logger.info("执行方法: " + joinPoint.getSignature().toShortString());

            // 继续执行原方法
            Object result = joinPoint.proceed();

            // 记录方法执行完成
            logger.info("执行完毕: " + joinPoint.getSignature().toShortString()
                    + " in " + (System.currentTimeMillis() - startTime) + "ms");

            return result;
        } catch (Exception e) {
            // 记录异常
            logger.error("Exception in method: " + joinPoint.getSignature().toShortString(), e);
            throw e;
        }
    }
}

