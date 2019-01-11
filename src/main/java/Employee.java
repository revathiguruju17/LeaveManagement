import java.util.List;

class Employee {
    private int ID;
    private String password;
    private EmployeeState employeeState;

    private int numberOfAnnualLeavesLeft;

    Employee(int ID, String password) {
        this.ID = ID;
        this.password = password;
        this.employeeState = EmployeeState.LOGOUT;
        this.numberOfAnnualLeavesLeft = 10;
    }

    EmployeeState getState() {
        return this.employeeState;
    }

    boolean checkID(int id) {
        return id == this.ID;
    }

    void updateNoOfLeavesLeft(LeaveState leaveState, int numberOfAnnualLeavesTaken) {
        if (leaveState == LeaveState.APPROVED) {
            this.numberOfAnnualLeavesLeft -= numberOfAnnualLeavesTaken;
        } else if (leaveState == LeaveState.PARTIALLY_APPROVED) {
            this.numberOfAnnualLeavesLeft = 0;
        }
    }

    void login(String password) {
        if (!this.password.equals(password)) {
            throw new LoginInvalidException("password is invalid");
        }
        this.employeeState = EmployeeState.LOGIN;
    }

    void logout() {
        employeeState = EmployeeState.LOGOUT;
    }

    int getNumberOfLeavesLeft() {
        return numberOfAnnualLeavesLeft;
    }

    public LeaveState approveLeave(int numberOfLeavesLeft, int numberOfLeaves) {
        return null;
    }


    List<Integer> getEmployeesOfTheApprover() {
        return null;
    }
}
