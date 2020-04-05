package com.dev.bruno.learning.stacks;

public class LinkedStack {

    private Node first;

    public LinkedStack() {
    }

    public void push(String value) {
        first = new Node(value, first);
    }

    public String pop() {
        if(isEmpty()) return null;
        String value = first.value;
        first = first.next;
        return value;
    }

    public boolean isEmpty() {
        return first == null;
    }

    private class Node {
        String value;

        Node next;

        Node(String value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    public static void main(String[] args) {
    }
}