package com.tylerbartnick.datastructures;

/**
 * A simple, performance-oriented implementation of a Stack data structure.
 * 
 * @author Tyler Bartnick
 * @version 1.0.0
 */
public class Stack<T> {
    /**
     * The internal representation of the Stack, a custom singly-linked list.
     */
    private SingleLinkedList<T> stack;

    /**
     * Default constructor creates an empty Stack.
     */
    public Stack() {
        this.stack = new SingleLinkedList<T>();
    }

    /**
     * The data to push onto the top of the Stack.
     * @param data The data to be added to the Stack.
     */
    public void push(T data) {
        if (data == null) { return; }
        stack.insert(new Node<T>(data), 0);
    }

    /**
     * Removes and returns the Node's data at the top of the Stack.
     * @return The data of the Node at the top of the Stack.
     */
    public T pop() {
        if (stack.getHead() == null) { return null; }

        T temp = stack.getHead().getData();
        stack.delete(0);
        return temp;
    }

    /**
     * Returns, but DOES NOT remove the data from the Node at the top of the stack.
     * Similar to pop(), but does not remove the Node in question, thus has no effect
     * on the size of the Stack.
     * @return The data of the Node at the top of the Stack.
     */
    public T peek() {
        if (stack.getHead() == null) { return null; }
        return stack.getHead().getData();
    }

    /**
     * Returns the total number of Nodes in the Stack.
     * @return The number of Nodes in the Stack.
     */
    public int count() {
        return stack.getCount();
    }

    /**
     * Tests for emptiness of the Stack. Returns true if empty, false otherwise.
     * @return true if empty, false otherwise.
     */
    public boolean empty() {
        return stack.empty();
    }

    /**
     * Empties the Stack. Nothing is returned.
     */
    public void clear() {
        stack.clear();
    }
}
