package model;

import view.IO;

public class Login {

    public String employeeLogin(IO io, Employees employees) {
        String ID = io.readInput();
        String password = io.readInput();
        boolean IDStatus = employees.checkID(ID);
        if (IDStatus) {
            boolean passwordStatus = employees.checkPassword(password);
            if (passwordStatus) {
                employees.updateState(ID);
                return "employee login successful";
            }
            return "password is invalid";
        }
        return "userID is invalid";
    }


}
