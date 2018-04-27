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
public class HttpAspect {

    private static final Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    @Pointcut("execution(public * com.hym.wxtest.web.*.*(..))")
    public void log(){
        //此处不执行逻辑语句
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        logger.info("doBefore--controller");
        //url
        logger.info("url={}",request.getRequestURL());
        //ip
        logger.info("ip={}",request.getRemoteAddr());
        //method
        logger.info("method={}",request.getMethod());
        //类方法
        logger.info("class_method={}",joinPoint.getSignature().getDeclaringType()+"."+joinPoint.getSignature().getName());
        //方法参数
        logger.info("args={}",joinPoint.getArgs());
    }

    @After("log()")
    public void doAfter(){}

    @AfterThrowing(pointcut = "log()", throwing = "e")
    public  void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        // 异常类方法
        logger.error("exception_class_method={}",joinPoint.getSignature().getDeclaringType() + "." + joinPoint.getSignature().getName());
        // 异常类型代码
        logger.error("exception_class={}", e.getClass().getName());
        // 异常信息
        logger.error("exception_message={}", e.getMessage());
    }

    @AfterReturning(pointcut = "log()",returning = "object")
    public void doAfterReturning(Object object){
        logger.info("response={}",object);
    }

}
