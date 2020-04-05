package com.dev.bruno.learning.queues;

import java.util.Iterator;

public interface Queue<T> extends Iterable<T>, Iterator<T> {

    void enqueue(T value);

    T dequeue();

    boolean isEmpty();
}
