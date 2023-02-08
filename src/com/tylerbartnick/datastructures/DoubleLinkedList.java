package com.tylerbartnick.datastructures;

import java.lang.IllegalArgumentException;
import java.lang.IndexOutOfBoundsException;
import java.lang.NullPointerException;

/**
 * A performance-oriented implementation of a doubly-linked list. Pointers to both
 * the head and the tail are kept at the class-level to ensure fast operations to
 * either end.
 * 
 * @author Tyler Bartnick
 * @version 1.0.0
 */
public class DoubleLinkedList<T> implements ILinkedList<T> {
    
    /**
     * The current number of Nodes in the list.
     */
    private int count;

    /**
     * The first Node in the list, may be null if list is empty.
     */
    private Node<T> head;

    /**
     * The last Node in the list, may be null if list is empty.
     */
    private Node<T> tail;

    /**
     * Default constructor to create an empty doubly-linked list.
     */
    public DoubleLinkedList() {
        this.count = 0;
        this.head = null;
        this.tail = null;
    }

    /**
     * Secondary constructor for the doubly-linked list. The given Node will be
     * the first/last Node when created.
     * 
     * @param data The data to add.
     */
    public DoubleLinkedList(T data) {
        try {
            // leverage the append functionality here to protect against errors
            append(data);
        } catch (IllegalArgumentException ex) {
            throw ex;
        }
    }

    /**
     * Gets a reference to the first Node in the list, if any.
     * @return The first Node in the list, null if list is empty.
     */
    public Node<T> getHead() {
        if (head == null) { return null; }
        return head;
    }

    /**
     * Gets a reference to the last Node in the list, if any.
     * 
     * @return The last Node in the list, null if empty. Will also be the same as head if list has one Node.
     */
    public Node<T> getTail() {
        if (tail == null) { return null; }
        return tail;
    }

    /**
     * Gets the number of Nodes in the list.
     * 
     * @return The current number of Nodes in the list.
     */
    public int getCount() {
        return count;
    }

    /**
     * Appends the given Node to the end of the list.
     * @param node The Node to be added.
     * @return The Node that was given.
     * @throws IllegalArgumentException
     */
    public T append(T data) throws IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException("Argument `data` cannot be null.", new NullPointerException());
        }

        Node<T> node = new Node<>(data);
        if (empty()) {
            head = node;
            tail = node;
        }
        else {
            Node<T> oldTail = getTail();
            oldTail.setNext(node);
            node.setPrev(oldTail);
            tail = node;
        }

        count++;
        return data;
    }

    /**
     * Inserts the data specified into the index specified.
     * 
     * @param data The data
     * @param index The index
     * @return The data supplied
     * @throws IllegalArgumentException
     */
    public T insert(T data, int index) throws IllegalArgumentException {
        if (index < 0 || index >= getCount()) {
            throw new IllegalArgumentException(new IndexOutOfBoundsException());
        }

        Node<T> node = new Node<>(data);
        if (index == 0) {
            head.setPrev(node);
            node.setNext(head);
            head = node;
        } else {
            Node<T> currPtr = getNodeAt(index);
            node.setNext(currPtr);
            node.setPrev(currPtr.getPrev());
            currPtr.getPrev().setNext(node);
            currPtr.setPrev(node);
        }

        count++;
        return data;
    }

    /**
     * Deletes the Node at the given index.
     * 
     * @param index The index of the Node to be deleted.
     * @throws IllegalArgumentException
     */
    public void delete(int index) throws IllegalArgumentException {
        if (index < 0 || index >= getCount()) {
            throw new IllegalArgumentException(new IndexOutOfBoundsException());
        }

        if (index == 0) {
            Node<T> temp = head;
            head = head.getNext();
            if (head != null) {
                head.setPrev(null);
                temp.setNext(null);
            } else {
                // if we're in here, we know we're trying to delete the last element
                tail.setPrev(null);
                tail = null;
            }
        } else if (index == getCount() - 1) {
            Node<T> temp = tail;
            tail = tail.getPrev();
            if (tail != null) {
                tail.setNext(null);
                temp.setPrev(null);
                // if we're in here, we know we're trying to delete the last element
                head.setPrev(null);
                head = null;
            }
        } else {
            Node<T> currPtr = getNodeAt(index);
            currPtr.getPrev().setNext(currPtr.getNext());
            currPtr.getNext().setPrev(currPtr.getPrev());
            deleteAllNodeData(currPtr);
        }

        count--;
    }

    /**
     * Gets the data at the given index.
     * 
     * @return The data at the given index.
     * @throws IllegalArgumentException
     */
    public T get(int index) throws IllegalArgumentException {
        if (index < 0 || index >= getCount()) {
            throw new IllegalArgumentException(new IndexOutOfBoundsException());
        }

        return getNodeAt(index).getData();
    }

    /**
     * Tests for emptiness of list.
     * @return true for empty list, false otherwise
     */
    public boolean empty() {
        return head == null && tail == null && count == 0;
    }

    /**
     * Deletes all items in the list.
     */
    public void clear() {
        if (head == null && tail == null && getCount() == 0) { return; }
        while (getCount() != 0) {
            delete(0);
        }
    }

    /**
     * Determines and returns the best point of entry for fastest traversal to a
     * given index.
     * @param index The index desired.
     * @return The Node for the best point of entry
     * @throws IllegalArgumentException
     */
    private Node<T> getTraversalEntryPoint(int index) throws IllegalArgumentException {
        if (index < 0 || index >= getCount()) {
            throw new IllegalArgumentException(new IndexOutOfBoundsException());
        }
        // calculate if faster to insert by traversing from head or tail
        return index > getCount() / 2 ? tail : head;
    }

    /**
     * Gets the Node at the given index.
     * @param index The index desired.
     * @return The Node at the given index, if exists.
     * @throws IllegalArgumentException
     */
    private Node<T> getNodeAt(int index) throws IllegalArgumentException {
        if (index < 0 || index >= getCount()) {
            throw new IllegalArgumentException(new IndexOutOfBoundsException());
        }

        // determine proper travesal direction for efficiency's sake and retrieve
        // the data at that node
        int idx = 0;
        Node<T> currPtr = getTraversalEntryPoint(index);
        if (currPtr == head) {
            while (currPtr.getNext() != null && idx < index) {
                currPtr = currPtr.getNext();
                idx++;
            }
        } else {
            idx = getCount() - 1;
            while (currPtr.getPrev() != null && idx > index) {
                currPtr = currPtr.getPrev();
                idx--;
            }
        }

        return currPtr;
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
