package com.xiaoshi.shop.proxy.staticproxy;

public class Client {
    public static void main(String[] args) {
        BuyCar customer = new Customer();
        CarProxy proxy = new CarProxy(customer);

        proxy.getInfo();
        proxy.buyCar();
    }
}
