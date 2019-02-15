package com.xiaoshi.shop.miaosha;

/**
 * @description: 模拟多个下单的用户
 * @author: xiaopang
 * @create: 2019-01-28 14:40
 **/
public class MyClient {
    public static void main(String[] args) {
        Order order = new Order();
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(order.buy(Thread.currentThread().getName()));
                }
            }).start();
        }
    }
}
