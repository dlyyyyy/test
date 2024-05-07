package com.dly.anno;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component("myAspect")
@Aspect  //标注当前myAspect是一个切面类
public class MyAspect {

//    @Before(value = "execution(* com.dly.anno.*.*())")
//    public void before(){
//        System.out.println("前置增强");
//    }
    public void afterReturning() {
        System.out.println("后置增强");
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("环绕前增强");
        Object proceed = pjp.proceed();
        System.out.println("环绕后增强");
        return proceed;
    }

    public void afterThrowing() {
        System.out.println("抛异常增强");
    }

    @After("MyAspect.pointcut()")
    public void after() {
        System.out.println("最终增强");
    }

    //定义切点表达式
    @Pointcut("execution(* com.dly.anno.*.*())")
    public void pointcut(){}
}
