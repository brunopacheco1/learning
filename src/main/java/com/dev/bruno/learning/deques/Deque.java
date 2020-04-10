package com.dev.bruno.learning.deques;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Item[] collection;
    private int head, tail;

    @SuppressWarnings("unchecked")
    public Deque() {
        collection = (Item[]) new Object[2];
        tail = 1;
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
        final int currentSize = size();
        final int shift = (newSize - currentSize) / 2;
        final Item[] copy = (Item[]) new Object[newSize];
        int copyIndex = shift;
        for (int index = head + 1; index < tail; index++) {
            copy[copyIndex++] = collection[index];
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
        return new DequeIterator(head + 1);
    }

    private class DequeIterator implements Iterator<Item> {
        private int currentIndex;

        DequeIterator(int currentIndex) {
            this.currentIndex = currentIndex;
        }

        public boolean hasNext() {
            return currentIndex < tail;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return collection[currentIndex++];
        }
    }

    public static void main(final String[] args) {
        final Deque<String> deque = new Deque<>();
        for (int i = 0; i < 200; i++) {
            deque.addFirst(i + "_test");
            deque.addLast(i + "_test");
        }
        for (String item : deque) {
            System.out.println(item);
        }
        for (int i = 0; i < 100; i++) {
            deque.removeFirst();
            deque.removeLast();
        }
        System.out.println();
    }
}
