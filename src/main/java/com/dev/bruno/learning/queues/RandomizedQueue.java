package com.dev.bruno.learning.queues;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] collection;
    private int tail;
    private int size;

    public RandomizedQueue() {
        collection = (Item[]) new Object[1];
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
        Item item = null;
        while (item == null) {
            int index = StdRandom.uniform(tail);
            item = collection[index];
            collection[index] = null;
        }
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
        Item item = null;
        while (item == null) {
            item = collection[StdRandom.uniform(tail)];
        }
        return item;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size;
    }

    private void doResizeIfNecessary() {
        if (tail == collection.length && size() <= collection.length / 2) {
            resize(collection.length);
        } else if (tail == collection.length) {
            resize(collection.length * 2);
        } else if (size() > 0 && size() == collection.length / 4) {
            resize(collection.length / 2);
        }
    }

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
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private final Item[] copy;
        private final int[] indices;
        private int currentIndex = 0;

        RandomizedQueueIterator() {
            this.copy = (Item[]) new Object[size];
            int newTail = 0;
            for (int i = 0; i < tail; i++) {
                if (collection[i] != null) {
                    this.copy[newTail++] = collection[i];
                }
            }
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
            return this.copy[indices[currentIndex++]];
        }
    }

    public static void main(String[] args) {
        final RandomizedQueue<String> queue = new RandomizedQueue<>();
        for (int i = 0; i < 2000; i++) {
            queue.enqueue(i + "_test");
            queue.enqueue(i + "_test");
            queue.dequeue();
        }
        int counter = 0;
        for (String item : queue) {
            System.out.println(item);
            System.out.println(++counter);
        }
        for (int i = 0; i < 2000; i++) {
            queue.dequeue();
        }
        queue.enqueue("final_test");
        System.out.println();
    }
}
