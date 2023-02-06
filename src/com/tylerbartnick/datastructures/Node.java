package com.tylerbartnick.datastructures;

/**
 * @author Tyler Bartnick
 * @version 1.0.0
 * 
 * A container class to hold data and refer to other nodes containing data.
 * For use in various data structures.
 */
public class Node<T> {
    /**
     * The next Node referred to by this Node.
     */
    private Node<T> next;

    /**
     * The previous Node referred to by this Node.
     */
    private Node<T> prev;

    /**
     * The actual data/payload of the Node.
     */
    private T data;

    /**
     * Default constructor for the class. All internal data is null.
     */
    public Node() {
        this.next = null;
        this.prev = null;
        this.data = null;
    }

    /**
     * Seconary constructor for class to specify the generic data held in object.
     * @param data The data held within the Node itself.
     */
    public Node(T data) {
        this.next = null;
        this.prev = null;
        this.data = data;
    }

    /**
     * Gets the next Node to which the Node refers.
     * @return The next Node.
     */
    public Node<T> getNext() {
        return this.next;
    }

    /**
     * Sets the next Node to which the Node refers.
     * @param node The Node to be set as the Node's next Node.
     */
    public void setNext(Node<T> node) {
        this.next = node;
    }

    /**
     * Gets the previous Node referred to by the Node.
     * @return The previous Node.
     */
    public Node<T> getPrev() {
        return this.prev;
    }

    /**
     * Sets the previous Node referred to by the Node.
     * @param node The Node to be set the previously referred to Node.
     */
    public void setPrev(Node<T> node) {
        this.prev = node;
    }

    /**
     * Gets the data contained within the Node.
     * @return The data contained by the Node.
     */
    public T getData() {
        return this.data;
    }

    /**
     * Sets the data contained within the Node.
     * @param data The data to be contained by the Node.
     */
    public void setData(T data) {
        this.data = data;
    }
}
