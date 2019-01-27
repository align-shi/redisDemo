package com.xiaoshi.shop.proxy.dynamic;

import java.lang.reflect.Proxy;

public class Client {
    public static void main(String[] args) {
        BuyCar customer = new Customer();

        CarHandler carHandler = new CarHandler(customer);

        BuyCar proxy = (BuyCar) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),new Class[]{BuyCar.class},carHandler);

        proxy.buyCar();
    }
}
