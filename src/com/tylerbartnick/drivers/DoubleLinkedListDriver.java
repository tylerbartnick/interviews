package com.tylerbartnick.drivers;

import com.tylerbartnick.datastructures.DoubleLinkedList;

public class DoubleLinkedListDriver {
    public static void main(String[] args) {
        DoubleLinkedList<String> ll = new DoubleLinkedList<>();

        System.out.println("Program started...");
        System.out.println("Current count of nodes in list: " + ll.getCount());
        System.out.println("Appending to list...");
        ll.append("First node");
        ll.append("Second node");
        ll.append("Third node");
        ll.insert("BETWEEN SECOND AND THIRD NODES", 2);

        System.out.println("Traversing list forwards...");
        for (int i = 0; i < ll.getCount(); i++) {
            System.out.println(ll.get(i));
        }

        System.out.println("....................");
        System.out.println("Traversing list backwards...");
        for (int i = ll.getCount() - 1; i >= 0; i--) {
            System.out.println(ll.get(i));
        }

        System.out.println("Clearing list...");
        ll.clear();
        System.out.println("Current count of nodes in list: " + ll.getCount());
    }
}
