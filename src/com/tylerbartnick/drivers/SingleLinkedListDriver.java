package com.tylerbartnick.drivers;

import com.tylerbartnick.datastructures.Node;
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

        Node<String> head = new Node<String>();
        head.setData("Original head/Node 1");
        ll.append(head);

        Node<String> newNode = new Node<String>();
        newNode.setData("Node 2");
        ll.append(newNode);

        System.out.println(ll.get(0).getData());

        Node<String> newNode2 = new Node<String>();
        newNode2.setData("Node 3");
        ll.append(newNode2);

        System.out.println(ll.getCount());

        Node<String> newNodeAtTail = new Node<String>();
        newNodeAtTail.setData("Originally inserted at tail.");
        ll.insert(newNodeAtTail, ll.getCount() - 1);

        System.out.println(ll.get(ll.getCount() - 1).getData());

        Node<String> newNodeAtHead = new Node<String>();
        newNodeAtHead.setData("Newly inserted node at head.");
        ll.insert(newNodeAtHead, 0);

        Node<String> newNodeAtIndex2 = new Node<String>();
        newNodeAtIndex2.setData("Newly inserted node at position 2.");
        ll.insert(newNodeAtIndex2, 2);

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

        Node<String> testNode = new Node<String>();
        testNode.setData("123");
        ll.insert(testNode, 0);

        System.out.println(ll.getHead().getData());
        ll.insert(new Node<String>("DATA!!!"), 1);

        System.out.println("Creating second linked list and inserting a Node immediately...");
        SingleLinkedList<Integer> ll2 = new SingleLinkedList<>(new Node<Integer>(12));
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
