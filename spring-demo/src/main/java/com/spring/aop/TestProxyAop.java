package com.spring.aop;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.util.Assert;

/**
 * @author brandon
 * create on 2019-06-25
 * desc:
 */
public class TestProxyAop {

    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory(new Hourse());
        proxyFactory.setInterfaces(Construction.class);
        proxyFactory.addAdvice(new BeforeConstructAdvice());
        proxyFactory.setExposeProxy(true);

        Construction proxy = (Construction)proxyFactory.getProxy();
        proxy.construct();
        Assert.isTrue(proxy.isPermitted(), "this is proxy didn't give a permission to build a hourse");
    }

}
