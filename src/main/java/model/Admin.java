package model;

public class Admin {

    boolean isAcceptLeave(int numberOfLeavesLeft, int numberOfLeavesWant){
        return numberOfLeavesLeft >= numberOfLeavesWant;
    }
}
