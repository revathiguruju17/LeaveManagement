package controller;

import model.*;
import view.IO;

import java.util.Date;

class LeaveManagement {
    void leaveProcess(IO io, Employee employee) {
        io.displayMessage("enter the number of leaves do you want to take");
        int numberOfLeaves = Integer.parseInt(io.readInput());
        io.displayMessage("enter the starting date and ending date in the format dd:mm:yyyy");
        String date1 = io.readInput();
        String date2 = io.readInput();
        String[] data1 = date1.split(":");
        String[] data2 = date2.split(":");
        Date startDate = new Date(Integer.parseInt(data1[2]), Integer.parseInt(data1[1]), Integer.parseInt(data1[0]));
        Date endDate = new Date(Integer.parseInt(data2[2]), Integer.parseInt(data2[1]), Integer.parseInt(data2[0]));
        io.displayMessage("enter the type of leave: ANNUAL/SICK leave");
        String typeOfLeave = io.readInput();
        LeaveType leaveType = LeaveType.valueOf(typeOfLeave);
        Leave leave = new Leave(numberOfLeaves, startDate, endDate, leaveType );
        Admin admin = new Admin();
        employee.applyLeave(leave, admin, io);
    }
}
