package com.dev.bruno.learning.stacks;

public class FixedCapacityStack {

    private String [] collection;
    private int lastIndex;

    public FixedCapacityStack(int N) {
        collection = new String[N];
    }

    public void push(String value) {
        collection[lastIndex] = value;
    }

    public String pop() {
        if(isEmpty()) return null;
        String value = collection[lastIndex];
        collection[lastIndex] = null;
        lastIndex--;
        return value;
    }

    public boolean isEmpty() {
        return lastIndex == 0;
    }

    public static void main(String[] args) {
    }
}