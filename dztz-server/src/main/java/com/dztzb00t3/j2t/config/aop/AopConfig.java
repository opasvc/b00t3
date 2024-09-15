package com.dztzb00t3.j2t.config.aop;

import com.alibaba.fastjson2.JSON;
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

//    @Around("@within(org.springframework.web.bind.annotation.RestController)")
//    public Object simpleAop(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        Object[] args = proceedingJoinPoint.getArgs();
//        System.out.println("入参: " + JSON.toJSONString(Arrays.stream(args)));
//        // 调用原有的方法
//        Object object = proceedingJoinPoint.proceed();
//        System.out.println("返参: " + object);
//        return object;
//    }

}
