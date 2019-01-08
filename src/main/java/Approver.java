import java.util.ArrayList;
import java.util.List;

class Approver extends Employee {
    private int ID;
    private String password;
    private int approver;
    private List<Leave> leaveRequests;
    private EmployeeState employeeState;

    Approver(int ID, String password, int approver) {
        super(ID, password, approver);
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
}
