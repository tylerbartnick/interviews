package com.tylerbartnick.drivers;

import com.tylerbartnick.datastructures.SingleLinkedList;

/**
 * Driver program to show correctness of algorithms found in the SinglyLinkedList<T> class.
 * This is a placeholder for more thorough JUnit tests.
 * 
 * @author Tyler Bartnick
 * @version 1.0.0
 */
public class SingleLinkedListDriver {
    public static void main(String[] args) {
        SingleLinkedList<String> ll = new SingleLinkedList<String>();
        ll.append("Original head/Node 1");
        ll.append("Node 2");

        System.out.println(ll.get(0));
        ll.append("Node 3");

        System.out.println(ll.getCount());
        ll.insert("Originally inserted at tail.", ll.getCount() - 1);
        System.out.println(ll.get(ll.getCount() - 1));

        ll.insert("Newly inserted node at head.", 0);
        ll.insert("Newly inserted node at position 2.", 2);
        ll.delete(0);
        ll.delete(2);
        
        try {
            ll.delete(ll.getCount());
        }
        catch (IllegalArgumentException ex) {
            ll.delete(ll.getCount() - 1);
        }

        System.out.println(ll.getCount());

        System.out.println(ll.empty());
        ll.clear();
        System.out.println(ll.empty());

        ll.insert("123", 0);

        System.out.println(ll.getHead());
        ll.insert("DATA!!!", 1);

        System.out.println("Creating second linked list and inserting a Node immediately...");
        SingleLinkedList<Integer> ll2 = new SingleLinkedList<>(12);
        System.out.println(ll2.getCount());
        System.out.println("Second linked list created successfully!");

        try {
            ll.insert(null, 0);
        }
        catch (IllegalArgumentException ex) {
            throw ex;
        }
    }
}
