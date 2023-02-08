package com.tylerbartnick.datastructures;

/**
 * Interface for all linked lists, both singly- and doubly-linked.
 * 
 * @author Tyler Bartnick
 * @version 1.0.0
 */
public interface ILinkedList<T> {
    public int getCount();
    public T append(T data);
    public T insert(T data, int index);
    public void delete(int index);
    public T get(int index);
    public boolean empty();
    public void clear();
}
