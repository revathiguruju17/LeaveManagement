package model;

import view.IO;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private String ID;
    private String password;
    private EmployeeState employeeState;
    private int numberOfAnnualLeavesLeft;
    private List<Leave> leavesTaken;

    public Employee(String ID, String password) {
        this.ID=ID;
        this.password=password;
        this.numberOfAnnualLeavesLeft = 24;
        leavesTaken = new ArrayList<>();
        employeeState = EmployeeState.LOGOUT;
    }

    boolean compareID(String id) {
        return this.ID.equals(id);
    }

    boolean comparePassword(String password) {
        return this.password.equals(password);
    }

    void updateNoOfLeaves(int numberOfAnnualLeavesTaken) {
        this.numberOfAnnualLeavesLeft -= numberOfAnnualLeavesTaken;
    }

    int getNumberOfAnnualLeavesLeft() {
        return numberOfAnnualLeavesLeft;
    }

    public void applyLeave(Leave leave, Admin admin, IO io){
        boolean statusOfLeave = admin.isAcceptLeave(numberOfAnnualLeavesLeft,leave.getNumberOfLeaves()) ;
        if(statusOfLeave) {
            addLeaveToTheLeaveList(leave);
            updateNoOfLeaves(leave.getNumberOfLeaves());
            io.displayMessage("leave accepted");
        }
        if(!statusOfLeave) {
            io.displayMessage("leave doesn't accepted");
        }
    }

    public void setEmployeeState(){
        if(this.employeeState==EmployeeState.LOGOUT){
            employeeState=EmployeeState.LOGIN;
        }
        else{
            employeeState=EmployeeState.LOGOUT;
        }
    }

    private void addLeaveToTheLeaveList(Leave leave) {
        leavesTaken.add(leave);
    }

    EmployeeState getState() {
        return this.employeeState;
    }
}
