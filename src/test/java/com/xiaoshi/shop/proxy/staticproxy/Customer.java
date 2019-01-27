package com.xiaoshi.shop.proxy.staticproxy;

public class Customer implements BuyCar {

    @Override
    public void getInfo() {
        System.out.println("顾客看车的信息");
    }

    @Override
    public void buyCar() {
        System.out.println("顾客付钱买车");
    }
}
