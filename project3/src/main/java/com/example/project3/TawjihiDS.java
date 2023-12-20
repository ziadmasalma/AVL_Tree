package com.example.project3;

import java.util.Comparator;

public class TawjihiDS {

    //to store allData the data
    private final CDLinkedList<Tawjihi> tawjihiList = new CDLinkedList<>();

    //avl tree to store the data sorted by the seat number
    private final AVLTree<Tawjihi> tawjihiTree_SeatNum = new AVLTree<>();
    private final AVLTree<Tawjihi> tawjihiTree_Avg = new AVLTree<>();

    //constructor
    public TawjihiDS() {
    }

    //add new data to the data structure
    public void add(Tawjihi tawjihi){
        if (seatNumberAlreadyExist(tawjihi.getSeatNumber()))
            throw new IllegalArgumentException("Seat number already exist");


        tawjihiList.insert(tawjihi);
        tawjihiTree_SeatNum.insert(tawjihi);
        tawjihiTree_Avg.insert(tawjihi, Comparator.comparingDouble(Tawjihi::getAverage));
    }

    //delete data from the data structure
    private void delete(Tawjihi tawjihi){
        tawjihiList.delete(tawjihi);
        tawjihiTree_SeatNum.delete(tawjihi);
        tawjihiTree_Avg.delete(tawjihi, Comparator.comparingDouble(Tawjihi::getAverage));
    }

    public void delete(int seatNumber){
        Tawjihi tawjihi = searchBySeatNum(seatNumber);
        delete(tawjihi);
    }

    //method to update the data
    private void update(Tawjihi tawjihiOld,Tawjihi tawjihiNew){
        CDNode<Tawjihi> temp = tawjihiList.getNode(tawjihiOld);
        if (temp != null){
            Tawjihi tawjihi = temp.getData();

            if (tawjihi.getSeatNumber() != tawjihiNew.getSeatNumber()){       //seat number changed
                if (seatNumberAlreadyExist(tawjihiNew.getSeatNumber())){
                    throw new RuntimeException("Seat number already exist");
                }
                tawjihi.setSeatNumber(tawjihiNew.getSeatNumber());

            }
            tawjihi.setBranch(tawjihiNew.getBranch());
            tawjihi.setAverage(tawjihiNew.getAverage());

        }else {
            throw new RuntimeException("Data not found");
        }
    }

    public void update(long seatNumberOld, long seatNumberNew, String branch, double average){
        Tawjihi tawjihiOld = new Tawjihi(seatNumberOld);
        Tawjihi tawjihiNew = new Tawjihi(seatNumberNew, branch, average);
        Tawjihi tawjihi = searchBySeatNum((int) seatNumberOld);
        tawjihiTree_Avg.delete(tawjihi, Comparator.comparingDouble(Tawjihi::getAverage));
        update(tawjihiOld, tawjihiNew);
        tawjihiTree_Avg.insert(tawjihi, Comparator.comparingDouble(Tawjihi::getAverage));
    }

    private boolean seatNumberAlreadyExist(long seatNumber) {
        return tawjihiTree_SeatNum.find(new Tawjihi(seatNumber),Comparator.comparingLong(Tawjihi::getSeatNumber)) != null;
    }

    //method to search for data by seat number
    public Tawjihi searchBySeatNum(int seatNum){               /* this can return null */
        Tawjihi temp = new Tawjihi(seatNum);
        LinkedList<Tawjihi> list = tawjihiTree_SeatNum.find(temp, Comparator.comparingLong(Tawjihi::getSeatNumber));
        if (list != null){
            if (list.size() == 1) {
                return list.getHead().getData();
            }

        }
        return null;
    }

    //getter
    public String getTawjihiList() {
        return tawjihiList.toString();
    }

    public String getTawjihiTree_SeatNum() {
        return tawjihiTree_SeatNum.printTreeLevelOrder();
    }
    public String getTawjihiTree_Avg() {
        return tawjihiTree_Avg.traverseInOrder();
    }
    public int getTawjihiTree_SeatNumheight(){
        return tawjihiTree_SeatNum.getHeight(tawjihiTree_SeatNum.root);
    }
    public int getTawjihiTree_Avgheight(){
        return tawjihiTree_Avg.getHeight(tawjihiTree_Avg.root);
    }
}
