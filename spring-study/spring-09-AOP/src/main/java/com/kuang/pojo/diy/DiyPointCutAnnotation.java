package com.kuang.pojo.diy;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class DiyPointCutAnnotation {
    @Before("execution(* com.kuang.pojo.service.UserServiceImpl.*(..))")
    public void before(){
        System.out.println("==使用注解插入前置通知==");
    }
    @After("execution(* com.kuang.pojo.service.UserServiceImpl.*(..))")
    public void after(){
        System.out.println("==使用注解插入后置通知==");
    }
    @Around("execution(* com.kuang.pojo.service.UserServiceImpl.*(..))")
    public void around(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("环绕前");

        Signature sg = jp.getSignature();
        System.out.println("signature:"+sg);

        Object proceed = jp.proceed();
        System.out.println("环绕后");
    }
}
