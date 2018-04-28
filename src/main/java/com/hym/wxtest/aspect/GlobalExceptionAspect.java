package com.hym.wxtest.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 打印请求信息、返回信息日志的拦截器
 */
@Aspect
@Component
public class GlobalExceptionAspect {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionAspect.class);

    @Pointcut("execution(public * com.hym.wxtest.handler.*.*(..))")
    public void log(){
        //此处不执行逻辑语句
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        logger.error("doBefore--GlobalExceptionHandler");
        //url
        logger.error("ip={}",request.getRemoteAddr());
        //method
        logger.error("method={}",request.getMethod());
        //类方法
        logger.error("class_method={}",joinPoint.getSignature().getDeclaringType()+"."+joinPoint.getSignature().getName());
        //方法参数
        logger.error("args={}",joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "log()",returning = "object")
    public void doAfterReturning(Object object){
        logger.error("global_exception_response={}",object);
    }

}
