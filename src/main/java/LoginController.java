

class LoginController {
    String login(IO ioMock, Employees employees) {
        String ID = ioMock.readInput();
        String password = ioMock.readInput();
        boolean IDStatus = employees.checkID(ID);
        if(IDStatus){
           boolean passwordStatus = employees.checkPassword(password);
           if(passwordStatus){
               return "login successful";
           }
           return "password is invalid";
        }
        return "userID is invalid";
    }
}
