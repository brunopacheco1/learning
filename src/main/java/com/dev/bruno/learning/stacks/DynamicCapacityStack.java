package com.dev.bruno.learning.stacks;

import java.util.Iterator;

public class DynamicCapacityStack<T> implements Stack<T> {

    private T[] collection;
    private int lastIndex;

    @SuppressWarnings("unchecked")
    public DynamicCapacityStack(int initialCapacity) {
        collection = (T[]) new Object[initialCapacity];
    }

    @Override
    public void push(T value) {
        if (lastIndex == collection.length) {
            resize(collection.length * 2);
        }
        collection[lastIndex] = value;
        lastIndex++;
    }

    @Override
    public T pop() {
        if (isEmpty())
            return null;
        if (lastIndex > 0 && lastIndex == collection.length / 4) {
            resize(collection.length / 2);
        }
        T value = collection[lastIndex];
        collection[lastIndex] = null;
        lastIndex--;
        return value;
    }

    @Override
    public boolean isEmpty() {
        return lastIndex == 0;
    }

    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        var copy = (T[]) new Object[newCapacity];
        for (int i = 0; i < lastIndex; i++) {
            copy[i] = collection[i];
        }
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
        return pop();
    }

    public static void main(String[] args) {
    }
}
