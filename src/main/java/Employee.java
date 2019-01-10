import java.util.ArrayList;
import java.util.List;

class Employee {
    private int ID;
    private String password;
    private EmployeeState employeeState;

    private int numberOfAnnualLeavesLeft;
    private List<Leave> leavesHistory;

    Employee(int ID, String password) {
        this.ID = ID;
        this.password = password;
        this.employeeState = EmployeeState.LOGOUT;
        this.numberOfAnnualLeavesLeft = 10;
        leavesHistory = new ArrayList<>();
    }

    List<Leave> getLeavesHistory() {
        return leavesHistory;
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

    void addLeaveToLeaveHistory(Leave leave) {
        this.leavesHistory.add(leave);
    }

    int getNumberOfLeavesLeft() {
        return numberOfAnnualLeavesLeft;
    }

    boolean checkLeaveRequester(int ID) {
        return false;
    }


    public List<Leave> getLeaveRequests() {
        return null;
    }

    public LeaveState approveLeave(int numberOfLeavesLeft, int numberOfLeaves) {
        return null;
    }
}
