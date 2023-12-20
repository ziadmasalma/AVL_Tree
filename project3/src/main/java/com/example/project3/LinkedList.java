package com.example.project3;

public class LinkedList<T extends Comparable<T>>{
    private LLNode<T> head;



    public LinkedList() {
        this.head = null;


    }

    public void add(T data) {
        LLNode<T> newNode = new LLNode<>(data);
        if (head == null) {
            head = newNode;
        }else {
            LLNode<T> currentNode = head;
            head = newNode;
            newNode.setNext(currentNode);
        }

    }

    //remove specific node in the list
    public void remove(T data) {
        if (head == null) {
            return;
        }
        if (head.getData().compareTo(data) == 0) {
            head = head.getNext();
            return;
        }
        LLNode<T> current = head;
        while (current.getNext() != null) {
            if (current.getNext().getData().compareTo(data) == 0) {
                current.setNext(current.getNext().getNext());
                return;
            }
            current = current.getNext();
        }
    }


    //get the size of the list
    public int size() {
        int size = 0;
        LLNode<T> current = head;
        while (current != null) {
            size++;
            current = current.getNext();
        }
        return size;
    }

    //method to get the head of the list
    public LLNode<T> getHead() {
        return head;
    }


    @Override
    public String toString() {
        LLNode<Tawjihi> curr= (LLNode<Tawjihi>) head;
        String s=curr.getData().getAverage()+"-->{";
        while (curr!=null){
            s+=curr.getData().getSeatNumber()+"-->";
            curr=curr.getNext();
        }
        s+="null}";
        return s;
    }
}
