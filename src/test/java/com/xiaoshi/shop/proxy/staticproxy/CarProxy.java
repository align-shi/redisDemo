package com.xiaoshi.shop.proxy.staticproxy;

public class CarProxy implements BuyCar {
    BuyCar car ;
    public CarProxy(BuyCar car) {
        this.car = car;
    }

    @Override
    public void getInfo() {
        System.out.println("中介提供代理的信息");
    }

    @Override
    public void buyCar() {
        System.out.println("****************");
        System.out.println("引导顾客买车");
        car.buyCar();
        System.out.println("顾客付钱买车成功");
        System.out.println("*****************");
    }
}
