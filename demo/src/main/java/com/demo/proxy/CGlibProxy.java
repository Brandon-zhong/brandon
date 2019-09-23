package com.demo.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author brandon
 * create on 2019-09-20
 * desc:
 */
public class CGlibProxy {

    public static void main(String[] args) {
        /*Proxy proxy = new Proxy();
        SayHello sayHello = (SayHello) proxy.getProxy(SayHello.class);
        sayHello.say();*/
        Class clazz = SayHello.class;
        System.out.println(clazz.getName()+"  "+clazz.getSimpleName());

    }


}

class SayHello {
    public void say() {
        System.out.println("SayHello.say");
    }
}

class Proxy implements MethodInterceptor  {

    private Enhancer enhancer = new Enhancer();

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("前置代理");
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("后置代理");
        return null;
    }

    public Object getProxy(Class clazz) {
        //设置父类
        enhancer.setSuperclass(clazz);
        //设置回调对象，会调用回调对象的interceptor方法
        enhancer.setCallback(this);
        return enhancer.create();
    }

}
