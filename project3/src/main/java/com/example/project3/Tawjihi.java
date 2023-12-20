package com.example.project3;


public class Tawjihi implements Comparable<Tawjihi> {


    private long seatNumber;
    private String branch;
    private double average;

    //constructor for dummy data
    public Tawjihi(long seatNumber){
        this.seatNumber = seatNumber;
        this.branch = null;
        this.average = -1;
    }

    //constructor for real data
    public Tawjihi(long seatNumber, String branch, double average) {
        this.seatNumber = seatNumber;
        this.branch = branch;
        this.average = average;
    }

    public long getSeatNumber() {
        return seatNumber;
    }

    public String getBranch() {
        return branch;
    }

    public double getAverage() {
        return average;
    }



    //setters
    public void setSeatNumber(long seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public void setAverage(double average) {
        this.average = average;
    }



    public String toString() {
        return "Seat Number: " + seatNumber + ", Branch: " + getBranch() + ", Average: " + average;
    }



    @Override
    public int compareTo(Tawjihi o) {
        return Long.compare(seatNumber, o.seatNumber);
    }

    //equals method on the seat number
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Tawjihi other = (Tawjihi) obj;
        return this.seatNumber == other.seatNumber;
    }

}
