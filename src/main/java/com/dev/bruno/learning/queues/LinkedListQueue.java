package com.dev.bruno.learning.queues;

import java.util.Iterator;

public class LinkedListQueue<T> implements Queue<T> {

    private Node first, last;

    public LinkedListQueue() {
    }

    @Override
    public void enqueue(T value) {
        Node node = new Node(value);
        if (isEmpty()) {
            first = node;
            last = node;
        } else {
            last.next = node;
            last = node;
        }
    }

    @Override
    public T dequeue() {
        if (isEmpty())
            return null;
        T value = first.value;
        first = first.next;
        if (isEmpty())
            last = null;
        return value;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    private class Node {
        T value;

        Node next;

        Node(T value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
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
        return dequeue();
    }
}
