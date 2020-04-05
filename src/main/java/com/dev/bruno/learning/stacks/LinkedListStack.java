package com.dev.bruno.learning.stacks;

import java.util.Iterator;

public class LinkedListStack<T> implements Stack<T> {

    private Node first;

    public LinkedListStack() {
    }

    @Override
    public void push(T value) {
        first = new Node(value, first);
    }

    @Override
    public T pop() {
        if (isEmpty())
            return null;
        T value = first.value;
        first = first.next;
        return value;
    }

    public boolean isEmpty() {
        return first == null;
    }

    private class Node {
        T value;

        Node next;

        Node(T value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        return !isEmpty();
    }

    @Override
    public T next() {
        return pop();
    }

    public static void main(String[] args) {
    }
}
