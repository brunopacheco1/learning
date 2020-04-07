package com.dev.bruno.learning.deques;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

public class Deque<Item> implements Iterable<Item> {

    private static final int INITIAL_SIZE = 32;
    private Item[] collection;
    private int head, tail;

    @SuppressWarnings("unchecked")
    public Deque() {
        collection = (Item[]) new Object[INITIAL_SIZE];
        tail = INITIAL_SIZE / 2;
        head = tail - 1;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return tail - head - 1;
    }

    public void addFirst(final Item item) {
        addValidationAndResizing(item);
        collection[head--] = item;
    }

    public void addLast(final Item item) {
        addValidationAndResizing(item);
        collection[tail++] = item;
    }

    private void addValidationAndResizing(final Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (head < 0 || tail == collection.length) {
            resize(collection.length * 2);
        }
    }

    @SuppressWarnings("unchecked")
    private void resize(final int newSize) {
        if (newSize < INITIAL_SIZE) {
            return;
        }
        final int currentSize = size();
        final int shift = (newSize - currentSize) / 2;
        final Item[] copy = (Item[]) new Object[newSize];
        for (int index = head + 1, copyIndex = shift; index < tail; index++, copyIndex++) {
            copy[copyIndex] = collection[index];
        }
        head = shift - 1;
        tail = shift + currentSize;
        collection = copy;
    }

    public Item removeFirst() {
        removeValidationAndResizing();
        final Item item = collection[++head];
        collection[head] = null;
        return item;
    }

    public Item removeLast() {
        removeValidationAndResizing();
        final Item item = collection[--tail];
        collection[tail] = null;
        return item;
    }

    private void removeValidationAndResizing() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (size() == collection.length / 4) {
            resize(collection.length / 2);
        }
    }

    @Override
    public Iterator<Item> iterator() {
        return null;
    }

    public static void main(final String[] args) {
        final Deque<String> deque = new Deque<>();
        IntStream.range(0, 100).forEach(i -> {
            deque.addFirst(i + "_test");
            deque.addLast(i + "_test");
        });
        IntStream.range(0, 100).forEach(i -> {
            deque.removeFirst();
            deque.removeLast();
        });
        System.out.println();
    }
}