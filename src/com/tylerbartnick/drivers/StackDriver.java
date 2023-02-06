package com.tylerbartnick.drivers;

import com.tylerbartnick.datastructures.Stack;

/**
 * Driver program to show correctness of algorithms found in the Stack<T> class.
 * This is a placeholder for more thorough JUnit tests.
 * 
 * @author Tyler Bartnick
 * @version 1.0.0
 */
public class StackDriver {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<Integer>();

        System.out.println("Current stack count: " + stack.count());
        System.out.println("Pushing 61...");
        stack.push(61);
        System.out.println("Pushing 31...");
        stack.push(31);
        System.out.println("Current stack count: " + stack.count());
        System.out.println("------------------");
        System.out.println("Beginning pop and print of all items in stack...");
        while (stack.count() > 0) {
            System.out.println(stack.pop());
        }
        System.out.println("Current stack count: " + stack.count());
        System.out.println("------------------");
        System.out.println("Ensuring stack can still be added to after popping all...");
        System.out.println("Pushing 15...");
        stack.push(15);
        System.out.println("Pushing 99...");
        stack.push(99);
        System.out.println("Current stack count: " + stack.count());

        System.out.println("Clearing stack...");
        stack.clear();
        System.out.println("Current stack count: " + stack.count());
        System.out.println("------------------");
        System.out.println("Ensuring stack can still be added to after completely clearing...");
        System.out.println("Pushing 34...");
        stack.push(34);
        System.out.println("Pushing 35...");
        stack.push(35);
        System.out.println("Current stack count: " + stack.count());
        stack.clear();
    }
}
