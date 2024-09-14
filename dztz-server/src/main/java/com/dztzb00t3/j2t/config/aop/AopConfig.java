package com.dztzb00t3.j2t.config.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * 简单的实现aop
 * todo aop
 *
 * @author j2t
 * @data 2024/09/14
 */
@Configuration
@Aspect
public class AopConfig {

    @Around("@within(org.springframework.web.bind.annotation.RestController)")
    public Object simpleAop(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();
        StringBuilder builder = new StringBuilder();
        Arrays.stream(args).forEach(e -> builder.append(e).append(" "));
        System.out.println("入参: " + builder.toString());
        // 调用原有的方法
        Object object = proceedingJoinPoint.proceed();
        System.out.println("返参: " + object);
        return object;
    }

}
