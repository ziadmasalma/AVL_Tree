package com.example.project3;


public class CDLinkedList<T extends Comparable<T>>{
    //circular doubly linked list
    private CDNode<T> head;
    private CDNode<T> tail;

    public CDLinkedList(){
        this.head = null;
        this.tail = null;
    }

    public CDNode<T> getHead(){
        return this.head;
    }

    public CDNode<T> getTail(){
        return this.tail;
    }

    public void setHead(CDNode<T> head){
        this.head = head;
    }

    public void setTail(CDNode<T> tail){
        this.tail = tail;
    }

    public void insert(T data){
        //inserts a node into the list
        CDNode<T> newNode = new CDNode<T>(data);
        if(this.head == null){
            this.head = newNode;
            this.tail = newNode;
            this.head.setNext(this.tail);
            this.head.setPrev(this.tail);
            this.tail.setNext(this.head);
            this.tail.setPrev(this.head);
        }
        else{
            this.tail.setNext(newNode);
            newNode.setPrev(this.tail);
            newNode.setNext(this.head);
            this.head.setPrev(newNode);
            this.tail = newNode;
        }
    }

    public void delete(T data){
        //deletes a node from the list
        if (this.head == null){
            return;
        }
        else if(this.head == this.tail){
            this.head = null;
            this.tail = null;
        }
        else{
            CDNode<T> current = this.head;
            while(current.getData().compareTo(data) != 0){
                current = current.getNext();
            }
            if(current == this.head){
                this.head = this.head.getNext();
                this.head.setPrev(this.tail);
                this.tail.setNext(this.head);
            }
            else if(current == this.tail){
                this.tail = this.tail.getPrev();
                this.tail.setNext(this.head);
                this.head.setPrev(this.tail);
            }
            else{
                current.getPrev().setNext(current.getNext());
                current.getNext().setPrev(current.getPrev());
            }
        }
    }

    public String toString(){
        //prints the list
        String s="";
        if(this.head == null){
            return "List is empty";
        }
        else{
            CDNode<T> current = this.head;
            while(current.getNext() != this.head){
                s+=current.getData() + "\n";
                current = current.getNext();
            }
            s+=current.getData();
            return s;
        }
    }

    public void printListReverse(){
        //prints the list in reverse
        if(this.head == null){
            System.out.println("List is empty");
        }
        else{
            CDNode<T> current = this.tail;
            while(current.getPrev() != this.tail){
                System.out.print(current.getData() + " ");
                current = current.getPrev();
            }
            System.out.println(current.getData());
        }
    }

    //size
    public int size(){
        int size = 0;
        if(this.head == null){
            return size;
        }
        else{
            CDNode<T> current = this.head;
            while(current.getNext() != this.head){
                size++;
                current = current.getNext();
            }
            size++;
        }
        return size;
    }

    //get node at specific data
    public CDNode<T> getNode(T data){
        if(this.head == null){
            return null;
        }
        else{
            CDNode<T> current = this.head;
            while(current.getData().compareTo(data) != 0 && current.getNext() != this.head){
                current = current.getNext();
            }
            if(current.getData().compareTo(data) == 0){
                return current;
            }
            else{
                return null;
            }

        }
    }

    public T search(T tawjihi) {
        //searches for a node in the list
        if (this.head == null){
            return null;
        }
        else{
            CDNode<T> current = this.head;
            while(!current.getData().equals(tawjihi) && current.getNext() != this.head){
                current = current.getNext();
            }
            if(current.getData().equals(tawjihi)){
                return current.getData();
            }
            else{
                return null;
            }
        }
    }

}

