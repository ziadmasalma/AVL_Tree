package com.example.project3;

public class CDNode<T extends Comparable<T>>{
    //node for the circular doubly linked list
    private T data;
    private CDNode<T> next;
    private CDNode<T> prev;


    public CDNode(T data){
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    public CDNode(T data, CDNode<T> next, CDNode<T> prev){
        this.data = data;
        this.next = next;
        this.prev = prev;
    }

    public T getData(){
        return this.data;
    }

    public CDNode<T> getNext(){
        return this.next;
    }

    public CDNode<T> getPrev(){
        return this.prev;
    }

    public void setData(T data){
        this.data = data;
    }

    public void setNext(CDNode<T> next){
        this.next = next;
    }

    public void setPrev(CDNode<T> prev){
        this.prev = prev;
    }

    public String toString(){
        return this.data.toString();
    }



}
