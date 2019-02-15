package com.xiaoshi.shop.controller;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: kafka消费者
 * @author: xiaopang
 * @create: 2019-02-15 16:02
 **/
@RestController
@RequestMapping("KafkaConsumer")
public class KafkaConsumer {

    @KafkaListener(topics = {"spring_topic"})
    public void testConsumer(ConsumerRecord<?,?> record) {
        System.out.println("testConsumer");
        System.out.println(record.value());
    }
    @KafkaListener(topics = {"spring_topic"},groupId = "xiaoshi")
    public void testConsumer1(ConsumerRecord<?,?> record) {
        System.out.println("testConsumer1");
        System.out.println(record.value());
    }
}
