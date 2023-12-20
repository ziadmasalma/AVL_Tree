package com.example.project3;

public class AVLNode<T extends Comparable<T>> {

    T data;
    int height=0;
    private AVLNode<T> left;
    private AVLNode<T> right;

    private final LinkedList<T> list = new LinkedList<>();         //list of data with the same key , if there is more than one data with the same key
    //it will be added to the list and the node itself will be -



    public AVLNode(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public AVLNode<T> getLeft() {
        return left;
    }

    public void setLeft(AVLNode<T> left) {
        this.left = left;
    }

    public AVLNode<T> getRight() {
        return right;
    }

    public void setRight(AVLNode<T> right) {
        this.right = right;
    }

    public boolean isLeaf() {
        return (left == null && right == null);
    }

    public boolean hasLeft() {
        return left != null;
    }

    public boolean hasRight() {
        return right != null;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public LinkedList<T> getList() {
        return list;
    }

    public String toString() {
        return "[" + data + "]";
    }


}
