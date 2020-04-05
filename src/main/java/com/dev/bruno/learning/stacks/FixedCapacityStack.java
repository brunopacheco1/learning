package com.dev.bruno.learning.stacks;

import java.util.Iterator;

public class FixedCapacityStack<T> implements Stack<T> {

    private T[] collection;
    private int lastIndex;

    @SuppressWarnings("unchecked")
    public FixedCapacityStack(int N) {
        collection = (T[]) new Object[N];
    }

    @Override
    public void push(T value) {
        collection[lastIndex] = value;
    }

    @Override
    public T pop() {
        if (isEmpty())
            return null;
        T value = collection[lastIndex];
        collection[lastIndex] = null;
        lastIndex--;
        return value;
    }

    @Override
    public boolean isEmpty() {
        return lastIndex == 0;
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
