package com.dztzb00t3.j2t.config.aop;

import lombok.extern.slf4j.Slf4j;
import jakarta.annotation.Resource;
import org.apache.ibatis.mapping.BoundSql;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.ProceedingJoinPoint;
import org.apache.ibatis.session.Configuration;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.SqlSessionFactory;


/**
 * 简单的实现aop
 * todo aop
 *
 * @author j2t
 * @data 2024/09/14
 */
@Slf4j
@Aspect
@Component
public class AopConfig {

    /**
     * 注入sql会话工厂
     */
    @Resource
    private SqlSessionFactory sqlSessionFactory;

    /**
     * 采用环绕通知<br/>
     * &#064;within是一个指示符，用于指定环绕通知应该应用在哪些类上。
     *
     * @param proceedingJoinPoint point
     * @return 输出参数和返回值
     * @throws Throwable throw
     */
    @Around("@within(org.springframework.web.bind.annotation.RestController)")
    public Object simpleAop(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();
        log.info("入参: {}", args);
        // 调用原有的方法
        Object object = proceedingJoinPoint.proceed();
        log.info("返参: {}", object);
        return object;
    }

    /**
     * 采用环绕通知
     *
     * @param point point
     * @return 截取打印 sql语句
     * @throws Throwable e
     */
    @Around("execution(* com.dztzb00t3.j2t.mapper.*.*(..))")
    public Object logSql(final ProceedingJoinPoint point) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = null;
        try {
            result = point.proceed(); // 执行目标方法
            String sql = getSql(point);
            log.info("\n本次执行Sql为: \n{};\n用时: {}ms\n----------------------------------------------------"
                    , sql
                    , System.currentTimeMillis() - startTime);
            return result;
        } catch (Throwable t) {
            log.info("异常: {}", t.getMessage());
            throw t;
        }
    }

    /**
     * @param point point
     * @return sql语句
     */
    private String getSql(ProceedingJoinPoint point) {
        // 从joinPoint获取方法签名和参数
        MethodSignature signature = (MethodSignature) point.getSignature();
        String namespace = signature.getDeclaringTypeName();
        String methodName = signature.getName();
        // 获取MappedStatement和BoundSql
        Configuration configuration = sqlSessionFactory.getConfiguration();
        MappedStatement mappedStatement = configuration.getMappedStatement(namespace + "." + methodName);
        BoundSql boundSql = mappedStatement.getBoundSql(point.getArgs());
        // 返回sql语句
        return boundSql.getSql();
    }

}
/*
    前置通知
        @Before("execution(* com.example.service.*.*(..))")
    后置通知
        @After("execution(* com.example.service.*.*(..))")
    返回通知
        @AfterReturning(pointcut = "execution(* com.example.service.*.*(..))", returning = "result")
    异常通知
        @AfterThrowing(pointcut = "execution(* com.example.service.*.*(..))", throwing = "error")
    环绕通知
        @Around("execution(* com.example.service.*.*(..))")
 */