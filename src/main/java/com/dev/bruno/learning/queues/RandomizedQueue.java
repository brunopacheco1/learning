package com.dev.bruno.learning.queues;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private static final int INITIAL_CAPACITY = 32;

    private Item[] collection;
    private int tail;
    private int size;

    @SuppressWarnings("unchecked")
    public RandomizedQueue() {
        collection = (Item[]) new Object[INITIAL_CAPACITY];
    }

    public void enqueue(Item value) {
        doResizeIfNecessary();
        collection[tail++] = value;
        size++;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        doResizeIfNecessary();
        int index = StdRandom.uniform(tail);
        Item item = collection[index];
        collection[index] = null;
        size--;
        if (size == 0) {
            tail = 0;
        }
        return item;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return collection[StdRandom.uniform(tail)];
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    private int size() {
        return size;
    }

    private void doResizeIfNecessary() {
        if (tail == collection.length && size() <= collection.length / 2) {
            resize(collection.length);
        } else if (tail == collection.length) {
            resize(collection.length * 2);
        } else if (size() == collection.length / 4) {
            resize(collection.length / 2);
        }
    }

    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        var copy = (Item[]) new Object[newCapacity];
        int newTail = 0;
        for (int i = 0; i < tail; i++) {
            if (collection[i] != null) {
                copy[newTail++] = collection[i];
            }
        }
        tail = newTail;
        collection = copy;
    }

    @Override
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator(collection, size);
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private Item[] collection;
        private final int[] indices;
        private int currentIndex = 0;

        public RandomizedQueueIterator(Item[] collection, int size) {
            this.collection = collection;
            this.indices = new int[size];
            for (int i = 0; i < size; i++) {
                indices[i] = i;
            }
            StdRandom.shuffle(indices);
        }

        public boolean hasNext() {
            return currentIndex < indices.length;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return collection[indices[currentIndex++]];
        }
    }
}
