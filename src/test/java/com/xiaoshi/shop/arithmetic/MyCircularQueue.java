package com.xiaoshi.shop.arithmetic;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 实现循环链表
 * @author: xiaopang
 * @create: 2019-01-24 10:13
 **/
public class MyCircularQueue {
        List<Integer> list;
        int p_start;
        int p_later;
        int size;
        int rear = 0;
        int front = 0;

        /** Initialize your data structure here. Set the size of the queue to be k. */
        public MyCircularQueue(int k) {
            list = new ArrayList<>(k);
            p_start = 0;
            p_later = 0;
            size  = k;

        }

        /** Insert an element into the circular queue. Return true if the operation is successful. */
        public boolean enQueue(int value) {
            if(isFull()){
                return false;
            }
            list.add(value);
            p_start++;
            if (rear > 0) rear =0;
            return true;
        }

        /** Delete an element from the circular queue. Return true if the operation is successful. */
        public boolean deQueue() {
            if(isEmpty()){
                return false;
            }
            p_later ++;
            if (front > 0) front =0;
            return true;
        }

        /** Get the front item from the queue. */
        public int Front() {

            if (isEmpty() || p_later + front > p_start){
                return -1;
            }
            return list.get( p_later + front++);
        }

        /** Get the last item from the queue. */
        public int Rear() {
            rear ++;
            if (isEmpty() || p_start - rear < p_later){
                return -1;
            }

            return list.get(p_start - rear);
        }

        /** Checks whether the circular queue is empty or not. */
        public boolean isEmpty() {
            return p_start == p_later;
        }

        /** Checks whether the circular queue is full or not. */
        public boolean isFull() {
            return p_start - p_later == size;
        }
}
