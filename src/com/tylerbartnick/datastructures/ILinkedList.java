package com.tylerbartnick.datastructures;

/**
 * Interface for all linked lists, both singly- and doubly-linked.
 * 
 * @author Tyler Bartnick
 * @version 1.0.0
 */
public interface ILinkedList<T> {
    public int getCount();
    public Node<T> append(Node<T> node);
    public Node<T> insert(Node<T> node, int index);
    public void delete(int index);
    public Node<T> get(int index);
    public boolean empty();
    public void clear();
}
