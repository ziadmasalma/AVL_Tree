package com.example.project3;

public class LLNode<T extends Comparable<T>> {
    private T data;
    private LLNode<T> next;

    public LLNode(T data) {
        this.data = data;
        this.next = null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public LLNode<T> getNext() {
        return next;
    }

    public void setNext(LLNode<T> next) {
        this.next = next;
    }
}
