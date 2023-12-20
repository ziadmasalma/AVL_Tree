package com.example.project3;


import java.util.Comparator;
import java.util.Queue;

public class AVLTree<T extends Comparable<T>> {


    AVLNode<T> root;


    public AVLTree() {
    }
    public void insert(T data, Comparator<T> comparator) {
        insert(data,root,comparator);
    }
    private void insert(T data, AVLNode<T> node, Comparator<T> comparator) {
        if(isEmpty()) root = new AVLNode<>(data);
        else {
            AVLNode<T> rootNode = root;
            addEntry(data, rootNode, comparator);
            root = balance(rootNode);
        }
    }
    public void addEntry(T data, AVLNode<T> rootNode, Comparator<T> comparator){
        assert rootNode != null;
        if(comparator.compare(data,rootNode.data) < 0){ // right into left subtree
            if(rootNode.hasLeft()){
                AVLNode<T> leftChild = rootNode.getLeft();
                addEntry(data, leftChild, comparator);
                rootNode.setLeft(balance(leftChild));
            }
            else rootNode.setLeft(new AVLNode<>(data));
        }
        else if (comparator.compare(data,rootNode.data ) > 0){ // right into right subtree
            if(rootNode.hasRight()){
                AVLNode<T> rightChild = rootNode.getRight();
                addEntry(data, rightChild, comparator);
                rootNode.setRight(balance(rightChild));
            }
            else rootNode.setRight(new AVLNode<>(data));
        }else {
            // if the data is already in the tree add it to the linked list
            if (rootNode.getList().size() == 0){
                T temp = rootNode.getData();
                rootNode.getList().add(temp);
                rootNode.getList().add(data);
                rootNode.setData((T) new Tawjihi(-1,null, ((Tawjihi) data).getAverage()));
            }else if (rootNode.getList().size() > 0){
                rootNode.getList().add(data);
            }
        }

    }

    public void insert(T key) {
        root = insert(root, key);
    }

    private AVLNode<T> insert(AVLNode<T> root, T key) {
        if (root == null) {
            return new AVLNode<T>(key);
        }
        if (key.compareTo(root.getData()) < 0) {
            root.setLeft(insert(root.getLeft(), key));
        } else {
            root.setRight(insert(root.getRight(), key));
        }
        return balance(root);
    }



    private AVLNode<T> balance(AVLNode<T> root) {
        if (root == null) {
            return root;
        }
        int balance = getBalance(root);
        if (balance > 1) {
            if (getBalance(root.getLeft()) > 0) {
                root = rotateRight(root);
            } else {
                root = rotateLeftRight(root);
            }
        } else if (balance < -1) {
            if (getBalance(root.getRight()) < 0) {
                root = rotateLeft(root);
            } else {
                root = rotateRightLeft(root);
            }
        }
        return root;
    }

    private AVLNode<T> rotateRightLeft(AVLNode<T> root) {
        AVLNode<T> temp = root.getRight();
        root.setRight(rotateRight(temp));
        return rotateLeft(root);
    }

    private AVLNode<T> rotateLeft(AVLNode<T> root) {
        AVLNode<T> temp = root.getRight();
        root.setRight(temp.getLeft());
        temp.setLeft(root);
        return temp;
    }

    private AVLNode<T> rotateLeftRight(AVLNode<T> root) {
        AVLNode<T> temp = root.getLeft();
        root.setLeft(rotateLeft(temp));
        return rotateRight(root);
    }

    private AVLNode<T> rotateRight(AVLNode<T> root) {
        AVLNode<T> temp = root.getLeft();
        root.setLeft(temp.getRight());
        temp.setRight(root);
        return temp;
    }

    private int getBalance(AVLNode<T> root) {
        if (root == null) {
            return 0;
        }
        return getHeight(root.getLeft()) - getHeight(root.getRight());
    }

    public int getHeight(AVLNode<T> curr) {
        if (curr == null)
            return 0;
        if (curr.isLeaf())
            return 1;
        else
            return Math.max(1 + getHeight(curr.getLeft()), 1 + getHeight(curr.getRight()));
    }
    public boolean isEmpty() {
        return root==null;
    }

    public String traverseInOrder() {
        return traverseInOrder(root);
    }
    private String traverseInOrder(AVLNode<T> node) {
        String s="";
        if (root == null) {
            return s;
        }
        Queue<AVLNode> queue = new java.util.LinkedList<>();
        queue.add(root);
        int currentLevel = 1;
        int nextLevel = 0;
        while (!queue.isEmpty()) {
            AVLNode<Tawjihi> current = queue.poll();
            if (current.getData().getSeatNumber()==-1)
                s+=current.getList().toString() + "\n";
            else
                s+=current.data.getAverage()+"-->{"+current.data.getSeatNumber()+"-->null}\n";
            if (current.getLeft() != null) {
                queue.add(current.getLeft());
                nextLevel++;
            }
            if (current.getRight() != null) {
                queue.add(current.getRight());
                nextLevel++;
            }
            currentLevel--;
            if (currentLevel == 0) {
                s+="\n";
                currentLevel = nextLevel;
                nextLevel = 0;
            }

        }
        return s;
    }

    // this method is used to print the tree in a graphical way

    public String printTreeLevelOrder() {
        return levelOrderTraversal(root);
    }
    private String levelOrderTraversal(AVLNode root) {
        String s="";
        if (root == null) {
            return s;
        }
        Queue<AVLNode> queue = new java.util.LinkedList<>();
        queue.add(root);
        int currentLevel = 1;
        int nextLevel = 0;
        while (!queue.isEmpty()) {
            AVLNode current = queue.poll();
            s+=current.data+ "\n";
            if (current.getLeft() != null) {
                queue.add(current.getLeft());
                nextLevel++;
            }
            if (current.getRight() != null) {
                queue.add(current.getRight());
                nextLevel++;
            }
            currentLevel--;
            if (currentLevel == 0) {
                s+="\n";
                currentLevel = nextLevel;
                nextLevel = 0;
            }

        }
        return s;
    }

    public void delete(T data) {
        root = delete(root, data);
    }
    private AVLNode<T> delete(AVLNode<T> node, T data) {
        if (node == null) {
            return node;
        }
        int compare = data.compareTo((T)node.data);
        if (compare < 0) {
            node.setLeft( delete(node.getLeft(), data));
        } else if (compare > 0) {
            node.setRight( delete(node.getRight(), data));
        } else {

            if (node.getList().size() != 0) {
                node.getList().remove(data);
                return node;
            }

            if (node.getLeft() == null && node.getRight() == null) {
                node = null;
            } else if (node.getLeft() == null) {
                node = node.getRight();
            } else if (node.getRight() == null) {
                node = node.getLeft();
            } else {
                AVLNode<T> minNode = findMin(node.getRight());
                node.data = minNode.data;
                node.setRight(delete(node.getRight(), minNode.data));
            }
        }
        if (node != null) {
            node = balance(node);
        }
        return node;
    }
    private AVLNode<T> findMin(AVLNode<T> node) {
        if (node.getLeft() == null) {
            return node;
        }
        return findMin(node.getLeft());
    }

    //delete method but depending on the given comparator
    public void delete(T data, Comparator<T> comparator) {
        root = delete(root, data, comparator);
    }
    private AVLNode<T> delete(AVLNode<T> node, T data, Comparator<T> comparator) {
        if (node == null) {
            return node;
        }
        int compare = comparator.compare(data, (T)node.data);
        if (compare < 0) {
            node.setLeft( delete(node.getLeft(), data, comparator));
        } else if (compare > 0) {
            node.setRight( delete(node.getRight(), data, comparator));
        } else {

            if (node.getList().size() != 0) {
                node.getList().remove(data);
                return node;
            }

            if (node.getLeft() == null && node.getRight() == null) {
                node = null;
            } else if (node.getLeft() == null) {
                node = node.getRight();
            } else if (node.getRight() == null) {
                node = node.getLeft();
            } else {
                AVLNode<T> minNode = findMin(node.getRight());
                node.data = minNode.data;
                node.setRight(delete(node.getRight(), minNode.data, comparator));
            }
        }
        if (node != null) {
            node = balance(node);
        }
        return node;
    }


    //find method but depending on the given comparator
    public LinkedList<T> find(T data, Comparator<T> comparator) {
        return find(root, data, comparator);
    }
    private LinkedList<T> find(AVLNode<T> node, T data, Comparator<T> comparator) {
        if (node == null) {
            return null;
        }
        int compare = comparator.compare(data, (T)node.data);
        if (compare < 0) {
            return find(node.getLeft(), data, comparator);
        } else if (compare > 0) {
            return find(node.getRight(), data, comparator);
        } else {
            if (node.getList().size() != 0) {
                return node.getList();
            }
            LinkedList<T> list = new LinkedList<>();
            list.add(node.data);
            return list;
        }
    }

}
