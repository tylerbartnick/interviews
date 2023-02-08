package com.tylerbartnick.datastructures;

import java.lang.IllegalArgumentException;
import java.lang.IndexOutOfBoundsException;
import java.lang.NullPointerException;

/**
 * Performance-oriented implementation of a singly-linked list.
 * 
 * @author Tyler Bartnick
 * @version 1.0.0
 */
public class SingleLinkedList<T> implements ILinkedList<T> {
    private int count;
    private Node<T> head;

    /**
     * Default constructor with empty references.
     */
    public SingleLinkedList() {
        this.count = 0;
        this.head = null;
    }

    /**
     * Constructor with an initial head Node.
     * @param data The data to be used as the head Node of the structure.
     */
    public SingleLinkedList(T data) {
        try {
            // leverage the append functionality here to protect against errors
            append(data);
        } catch (IllegalArgumentException ex) {
            throw ex;
        }
    }

    /**
     * Gets the data of the head Node.
     * @return The data of the head Node.
     */
    public T getHead() {
        if (head == null) { return null; }
        return head.getData();
    }

    /**
     * Gets the number of elements (Nodes) in the list at the moment.
     * @return The number of elements (Nodes) in the list.
     */
    public int getCount() {
        return count;
    }

    /**
     * Appends the specified Node to the end of the list. Requires full traversal.
     * @return The Node that was to be appended.
     * @throws IllegalArgumentException
     */
    public T append(T data) throws IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException(new NullPointerException());
        }

        Node<T> node = new Node<>(data);
        Node<T> currPtr = head;
        if (currPtr == null) {
            // we are appending to an empty list
            head = node;
            count++;
            return data;
        }
        
        while (currPtr.getNext() != null) {
            currPtr = currPtr.getNext();
        }
        currPtr.setNext(node);
        count++;
        return data;
    }

    /**
     * Inserts the specified Node at the specified index.
     * @return The specified Node.
     * @throws IllegalArgumentException
     */
    public T insert(T data, int index) throws IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException(new NullPointerException());
        }

        if (index < 0 || index > getCount()) {
            throw new IllegalArgumentException(new IndexOutOfBoundsException());
        }

        Node<T> node = new Node<>(data);
        if (index == 0) {
            // insert at head
            node.setNext(head);
            head = node;
            count++;
            return data;
        } else if (index == 1 || index == getCount() - 1) {
            // insert at tail, just call append() which will also increment count
            // position 1 is a special case to account for when list only has a head
            return append(data);
        } else {
            // insert in the middle somewhere
            int idx = 0;
            Node<T> currPtr = head;
            Node<T> currPtrMinusOne = null;
            while (head.getNext() != null && idx < index) {
                currPtrMinusOne = currPtr;
                currPtr = currPtr.getNext();
                idx++;
            }
            currPtrMinusOne.setNext(node);
            node.setNext(currPtr);
            count++;
            return data;
        }
    }

    /**
     * Deletes the Node at the specified index, if exists.
     * @throws IllegalArgumentException
     */
    public void delete(int index) throws IllegalArgumentException {
        if (head == null || index < 0 || index >= getCount()) {
            throw new IllegalArgumentException(new IndexOutOfBoundsException());
        }

        if (index == 0) {
            // deletion occurs at beginning of list, advance head ptr and remove
            // all refs to original head.
            Node<T> temp = head;
            head = head.getNext();
            // ensure node will get gc'd
            deleteAllNodeData(temp);
            count--;
        } else if (index == getCount() - 1) {
            // since no prev ref, need to keep track of last visited and current
            Node<T> currPtr = head;
            Node<T> currPtrMinusOne = null;
            while (currPtr.getNext() != null) {
                currPtrMinusOne = currPtr;
                currPtr = currPtr.getNext();
            }
            currPtrMinusOne.setNext(null);
            deleteAllNodeData(currPtr);
            count--;
        } else {
            // we know we are deleting from the middle somewhere
            int idx = 0;
            Node<T> currPtr = head;
            Node<T> currPtrMinusOne = null;
            while (currPtr.getNext() != null && idx < index) {
                currPtrMinusOne = currPtr;
                currPtr = currPtr.getNext();
                idx++;
            }
            currPtrMinusOne.setNext(currPtr.getNext());
            deleteAllNodeData(currPtr);
            count--;
        }
    }

    /**
     * Retrieves and returns the Node at the specified index, if exists.
     * @return The Node at the specified index, if exists. Null if not found.
     * @throws IllegalArgumentException
     */
    public T get(int index) throws IllegalArgumentException {
        if (head == null || index < 0 || index > getCount()) {
            throw new IllegalArgumentException(new IndexOutOfBoundsException());
        }

        int idx = 0;
        Node<T> currPtr = head;
        while (currPtr.getNext() != null && idx < index) {
            currPtr = currPtr.getNext();
            idx++;
        }
        return currPtr.getData();
    }

    /**
     * Method to detemine if the collection is empty or not.
     * @return True if list is null or contains no elements/Nodes.
     */
    public boolean empty() {
        return head == null ? true : false;
    }

    /**
     * Method to clear the collection of all Nodes.
     */
    public void clear() {
        if (head == null) { return; }
        Node<T> currPtr = head;
        Node<T> currPtrMinusOne = null;
        while (currPtr.getNext() != null) {
            currPtrMinusOne = head;
            head = head.getNext();
            deleteAllNodeData(currPtrMinusOne);
        }
        deleteAllNodeData(head);
        head = null;
        count = 0;
    }

    /**
     * Private helper method to ensure all references to other Nodes on a specified Node
     * are cleared. This is intended to aid garbage collection and prevent memeory leaks.
     * It is also called to ensure a newly inserted Node has the proper links set up.
     * @param node The Node to be stripped of all references.
     */
    private void eraseNodeRefs(Node<T> node) {
        if (node == null) { return; }
        node.setNext(null);
        node.setPrev(null);
    }

    /**
     * Private helper method to ensure all data and references to other Nodes on a specified
     * Node are cleared. This is intended to aid garbage collection and prevent memeory leaks.
     * @param node The Node to be stripped of all data and references.
     */
    private void deleteAllNodeData(Node<T> node) {
        if (node == null) { return; }
        eraseNodeRefs(node);
        node.setData(null);
    }
}
