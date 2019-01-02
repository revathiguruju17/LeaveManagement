

class LoginController {
    String login(IO ioMock, Employees employees) {
        String status="login unsuccessful";
        String ID = ioMock.readInput();
        String password = ioMock.readInput();
        boolean loginStatus = employees.checkIDAndPassword(ID,password);
        if(loginStatus)
            return "login successful";
        return status;
    }
}
