package com.dev.bruno.learning.queues;

import java.util.Iterator;

public class DynamicCapacityQueue<T> implements Queue<T> {

    private T[] collection;
    private int head;
    private int tail;

    @SuppressWarnings("unchecked")
    public DynamicCapacityQueue(final int initialCapacity) {
        collection = (T[]) new Object[initialCapacity];
    }

    @Override
    public void enqueue(final T value) {
        if (tail == collection.length) {
            resize(collection.length * 2);
        }
        collection[tail] = value;
        tail++;
    }

    @Override
    public T dequeue() {
        if (isEmpty())
            return null;
        if (tail > head && tail == collection.length / 4) {
            resize(collection.length / 2);
        }
        final T value = collection[head];
        collection[head] = null;
        head++;
        if (head == tail) {
            head = 0;
            tail = 0;
        }
        return value;
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @SuppressWarnings("unchecked")
    private void resize(final int newCapacity) {
        final var copy = (T[]) new Object[newCapacity];
        for (int i = head; i < tail; i++) {
            copy[i - head] = collection[i];
        }
        tail = tail - head;
        head = 0;
        collection = copy;
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

    public static void main(final String[] args) {
    }
}
