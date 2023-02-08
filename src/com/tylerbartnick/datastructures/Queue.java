package com.tylerbartnick.datastructures;

/**
 * Implementation of a Queue using a doubly-linked list. Supports operations such as
 * inserting and removing data in constant time.
 */
public class Queue<T> {
    /**
     * The internal representation of the queue, a double-linked list.
     */
    private DoubleLinkedList<T> queue;

    /**
     * Default constructor, creates an empty queue.
     */
    public Queue() {
        queue = new DoubleLinkedList<>();
    }

    /**
     * 
     * @param data
     */
    public Queue(T data) {
        queue = new DoubleLinkedList<>(data);
    }

    /**
     * Add the data supplied to the end of the queue. A constant time operation.
     * @param data The data to add
     * @return The data added
     */
    public T enqueue(T data) {
        return queue.append(data);
    }

    /**
     * Remove and return the data at the head of the Queue. A constant time operation.
     * @return The data at the head of the Queue
     */
    public T dequeue() {
        T temp = queue.get(0);
        queue.delete(0);
        return temp;
    }

    /**
     * Gets the number of elements in the queue. A constant time operation.
     * @return The number of elements in the queue.
     */
    public int count() {
        return queue.getCount();
    }
    
    /**
     * Determines if the queue is emtpy. True if so, false otherwise.
     * @return true if empty, false otherwise
     */
    public boolean empty() {
        return queue.empty();
    }

    /**
     * Clears all elements in the queue.
     */
    public void clear() {
        queue.clear();
    }

    /**
     * Gets and returns the data at the head of the queue, but does not remove it from the queue.
     * @return The data at the head of the queue
     */
    public T peekHead() {
        return queue.get(0);
    }

    /**
     * Gets and returns the data at the tail of the queue, but does not remove it from the queue.
     * @return The data at the tail of the queue
     */
    public T peekTail() {
        return queue.get(queue.getCount() - 1);
    }
}
