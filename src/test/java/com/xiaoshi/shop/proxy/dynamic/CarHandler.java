package com.xiaoshi.shop.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CarHandler implements InvocationHandler {
    BuyCar customer;

    public CarHandler(BuyCar customer) {
       this. customer = customer;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("********");
        method.invoke(customer, args);
        System.out.println("********");
        return null;
    }
}
