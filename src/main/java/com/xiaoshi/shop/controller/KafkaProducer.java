package com.xiaoshi.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @description: kafka的生产者
 * @author: xiaopang
 * @create: 2019-02-15 15:15
 **/
@RestController
@EnableAutoConfiguration
@RequestMapping("KafkaProducer")
public class KafkaProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @RequestMapping("/testSend")
    public void testSend() {
        String msg = UUID.randomUUID().toString();
        ListenableFuture future =kafkaTemplate.send("spring_topic", msg);
        //使用回调函数判断是否成功
        future.addCallback(new ListenableFutureCallback() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("失败");
            }

            @Override
            public void onSuccess(Object o) {
                System.out.println("成功");
            }
        });
    }
}
