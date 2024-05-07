package com.dly.proxy.cglib;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class ProxyTest {
    public static void main(String[] args) {
        //目标对象
        final Target target = new Target();

        //增强对象
        final Enhance enhance = new Enhance();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Target.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                enhance.before();
                method.invoke(target,args);
                enhance.after();
                return null;
            }
        });
        Target proxy = (Target) enhancer.create();
        proxy.save();

    }
}
