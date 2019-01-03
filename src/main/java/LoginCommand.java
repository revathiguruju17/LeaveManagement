class LoginCommand {
    void execute(Organization organization, String ID, String password){
        organization.employeeLogin(ID, password);
    }
}
