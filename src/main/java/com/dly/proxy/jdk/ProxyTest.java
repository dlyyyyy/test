package com.dly.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {
    public static void main(String[] args) {
        //目标对象
        final Target target = new Target();

        //增强对象
        final Enhance enhance = new Enhance();
        TargetInterface proxy = (TargetInterface) Proxy.newProxyInstance(
                //目标对象的类加载器
                target.getClass().getClassLoader(),
                //目标对象相同的接口字节码对象数组
                target.getClass().getInterfaces(),
                new InvocationHandler() {

                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        enhance.before();
                        Object invoke = method.invoke(target, args);
                        enhance.after();
                        return invoke;
                    }
                }
        );

        proxy.save();
    }
}
