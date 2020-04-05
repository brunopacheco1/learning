package com.dev.bruno.learning.stacks;

import java.util.Iterator;

public interface Stack<T> extends Iterable<T>, Iterator<T> {

    void push(T value);

    T pop();

    boolean isEmpty();
}
