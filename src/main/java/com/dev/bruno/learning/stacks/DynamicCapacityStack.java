package com.dev.bruno.learning.stacks;

public class DynamicCapacityStack {

    private String[] collection;
    private int lastIndex;

    public DynamicCapacityStack(int initialCapacity) {
        collection = new String[initialCapacity];
    }

    public void push(String value) {
        if (lastIndex == collection.length) {
            resize(collection.length * 2);
        }
        collection[lastIndex] = value;
        lastIndex++;
    }

    public String pop() {
        if (isEmpty())
            return null;
        if (lastIndex > 0 && lastIndex == collection.length / 4) {
            resize(collection.length / 2);
        }
        String value = collection[lastIndex];
        collection[lastIndex] = null;
        lastIndex--;
        return value;
    }

    public boolean isEmpty() {
        return lastIndex == 0;
    }

    public void resize(int newCapacity) {
        var copy = new String[newCapacity];
        for (int i = 0; i < lastIndex; i++) {
            copy[i] = collection[i];
        }
        collection = copy;
    }

    public static void main(String[] args) {
    }
}