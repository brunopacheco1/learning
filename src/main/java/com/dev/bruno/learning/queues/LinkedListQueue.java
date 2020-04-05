package com.dev.bruno.learning.queues;

public class LinkedListQueue {

    private Node first, last;

    public LinkedListQueue() {
    }

    public void enqueue(String value) {
        Node node = new Node(value);
        if (isEmpty()) {
            first = node;
            last = node;
        } else {
            last.next = node;
            last = node;
        }
    }

    public String dequeue() {
        if (isEmpty())
            return null;
        String value = first.value;
        first = first.next;
        if (isEmpty())
            last = null;
        return value;
    }

    public boolean isEmpty() {
        return first == null;
    }

    private class Node {
        String value;

        Node next;

        Node(String value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
    }
}