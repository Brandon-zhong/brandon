package com.spring.aop;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author brandon
 * create on 2019-06-25
 * desc:
 */
public class BeforeConstructAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        if (method.getName().equals("construct")) {
            ((Construction) target).givePermission();
        }
    }
}
