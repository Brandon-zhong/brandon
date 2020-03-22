package com.demo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author brandon
 * Created on 2020-03-22.
 * desc: jdk proxy demo
 **/
public class JDKProxy {

    interface Demo {
        void doSomeThing();
    }

    static class DemoProxy implements InvocationHandler {

        private Demo demo;

        public DemoProxy(Demo demo) {
            this.demo = demo;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("DemoProxy.invoke -- before");
            demo.doSomeThing();
            System.out.println("DemoProxy.invoke -- after");
            return null;
        }

        public Demo newInstance() {
            return (Demo) Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{Demo.class}, this);
        }
    }

    public static void main(String[] args) {
        Demo demo = new DemoProxy(() -> System.out.println("JDKProxy.doSomeThing -- real")).newInstance();
        demo.doSomeThing();
    }


}
