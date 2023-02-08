package com.tylerbartnick.drivers;

import com.tylerbartnick.datastructures.Queue;

public class QueueDriver {
    public static void main(String[] args) {
        Queue<Integer> q = new Queue<>();

        System.out.println("Adding numbers 1 to 100 to the queue...");
        for (int i = 1; i <= 100; i++) {
            q.enqueue(i);
        }
        System.out.println("Done adding numbers 1 to 100.");

        System.out.println("Current value at head of queue: " + q.peekHead());
        System.out.println("Current value at tail of queue: " + q.peekTail());

        System.out.println("Beginning dequeue and print of all items in queue...");
        while (!q.empty()) {
            System.out.println(q.dequeue());
        }
        System.out.println("Items in queue: " + q.count());

        System.out.println("Redoing entire process over again to ensure program works as expected...");

        System.out.println("Adding numbers 1 to 100 to the queue...");
        for (int i = 1; i <= 100; i++) {
            q.enqueue(i);
        }
        System.out.println("Done adding numbers 1 to 100.");

        System.out.println("Current value at head of queue: " + q.peekHead());
        System.out.println("Current value at tail of queue: " + q.peekTail());

        System.out.println("Beginning dequeue and print of all items in queue...");
        while (!q.empty()) {
            System.out.println(q.dequeue());
        }
        System.out.println("Items in queue: " + q.count());

        System.out.println("DONE!");
    }
}
