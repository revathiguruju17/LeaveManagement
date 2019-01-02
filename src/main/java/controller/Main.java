package controller;

import model.Employee;
import model.Employees;
import model.Login;
import view.ConsoleIO;
import view.IO;

public class Main {
    public static void main(String[] args) {
        LeaveManagement leaveManagement = new LeaveManagement();
        IO io = new ConsoleIO();
        Employees employees = new Employees();
        employees.addEmployeesToTheList(employees);
        io.displayMessage("Please login to see the details");
        Login login = new Login();
        String message;
        do {
            message = login.employeeLogin(io, employees);
            io.displayMessage(message);
        } while (!message.equals("employee login successful"));
        Employee employee = employees.getEmployee();
        io.displayMessage("Do you want to take the leave: Type yes/no");
        if (io.readInput().equals("yes")) {
            leaveManagement.leaveProcess(io, employee);
        }
        io.displayMessage("do you want to logout: type yes/no");
        String userChoice = io.readInput();
        if(userChoice.equals("yes")){
            employee.setEmployeeState();
            io.displayMessage("successful logout");
        }
    }

}
