package com.qxy.spring.test;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * @Author:wx
 * @Date:2020/5/28 20:55
 */
@Aspect
public class AspectJTest {

    @Pointcut("execution(* *.test(..))")
    public void test() {

    }

    @Before("test()")
    public void beforeTest() {
        System.out.println("before...");
    }

    @After("test()")
    public void afterTest() {
        System.out.println("after...");
    }

    @Around("test()")
    public Object aroundTest(ProceedingJoinPoint point) {
        System.out.println("around before...");
        Object proceed = null;
        try {
             proceed = point.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("around after...");
        return proceed;
    }
}
