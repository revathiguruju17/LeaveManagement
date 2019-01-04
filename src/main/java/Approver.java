import java.util.ArrayList;
import java.util.List;

class Approver {
    private String ID;
    private String password;
    private List<Leave> leaveRequests;
    private EmployeeState employeeState;

    Approver(String ID, String password) {
        this.ID = ID;
        this.password = password;
        this.employeeState = EmployeeState.LOGOUT;
        this.leaveRequests = new ArrayList<>();
    }

    LeaveState approveLeave(int numberOfLeavesLeft, int numberOfLeavesWant) {
        if (numberOfLeavesLeft == 0) {
            return LeaveState.REJECTED;
        } else if (numberOfLeavesLeft >= numberOfLeavesWant)
            return LeaveState.APPROVED;
        else {
            return LeaveState.PARTIALLY_APPROVED;
        }
    }

    void addLeaveRequest(Leave leave) {
        leaveRequests.add(leave);
    }

    void login() {
        this.employeeState = EmployeeState.LOGIN;
    }

    void logout() {
        this.employeeState = EmployeeState.LOGOUT;
    }

    EmployeeState getEmployeeState() {
        return employeeState;
    }
}
