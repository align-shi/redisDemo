package com.xiaoshi.shop.arithmetic;

/**
 * @description: 测试循环链表
 * @author: xiaopang
 * @create: 2019-01-24 10:10
 **/
public class ListQueen {

    public static void main(String args[]){
        MyCircularQueue circularQueue = new MyCircularQueue(6); // 设置长度为3

        System.out.println(circularQueue.enQueue(6));  // 返回true

        System.out.println(circularQueue.Rear());  // 返回true

        System.out.println(circularQueue.Rear());  // 返回true

        System.out.println(circularQueue.deQueue());  // 返回false,队列已满

        System.out.println(circularQueue.enQueue(5));  // 返回3

        System.out.println(circularQueue.Rear());  // 返回true

        System.out.println(circularQueue.deQueue());  // 返回false,队列已满

        System.out.println(circularQueue.Front());

        System.out.println(circularQueue.deQueue());  // 返回false,队列已满

        System.out.println(circularQueue.deQueue());  // 返回false,队列已满

        System.out.println(circularQueue.deQueue());  // 返回false,队列已满

       /* MyCircularQueue circularQueue = new MyCircularQueue(3); // 设置长度为3

        System.out.println(circularQueue.enQueue(1));  // 返回true

        System.out.println(circularQueue.enQueue(2));  // 返回true

        System.out.println(circularQueue.enQueue(3));  // 返回true

        System.out.println(circularQueue.enQueue(4));  // 返回false,队列已满

        System.out.println(circularQueue.Rear());  // 返回3

        System.out.println(circularQueue.isFull());  // 返回true

        System.out.println(circularQueue.deQueue());  // 返回true

        System.out.println(circularQueue.enQueue(4));  // 返回true

        System.out.println(circularQueue.Rear());  // 返回4*/
    }
}
