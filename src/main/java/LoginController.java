

class LoginController {
    String login(IO io, Employees employees) {
        String ID = io.readInput();
        String password = io.readInput();
        boolean IDStatus = employees.checkID(ID);
        if(IDStatus){
           boolean passwordStatus = employees.checkPassword(password);
           if(passwordStatus){
               io.displayMessage("login successful");
               return "login successful";
           }
           io.displayMessage("password is invalid");
           return "password is invalid";
        }
        io.displayMessage("userID is invalid");
        return "userID is invalid";
    }
}
