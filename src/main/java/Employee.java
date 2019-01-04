import java.util.ArrayList;
import java.util.List;

class Employee {

    private String ID;
    private String password;
    private EmployeeState employeeState;
    private int numberOfAnnualLeavesLeft;
    private List<Leave> leavesHistory;

    Employee(String ID, String password) {
        this.ID = ID;
        this.password = password;
        this.numberOfAnnualLeavesLeft = 10;
        leavesHistory = new ArrayList<>();
        employeeState = EmployeeState.LOGOUT;
    }

    String getID() {
        return ID;
    }

    int getNumberOfAnnualLeavesLeft() {
        return numberOfAnnualLeavesLeft;
    }

    boolean compareUserIDAndPassword(String ID, String password) {
        return this.ID.equals(ID) && this.password.equals(password);
    }

    private void updateNoOfLeavesLeft(int numberOfAnnualLeavesTaken) {
        this.numberOfAnnualLeavesLeft -= numberOfAnnualLeavesTaken;
    }

    EmployeeState getState() {
        return this.employeeState;
    }

    void login() {
        employeeState = EmployeeState.LOGIN;
    }

    void logout() {
        employeeState = EmployeeState.LOGOUT;
    }

    LeaveState applyLeave(Leave leave, Approver approver) {
        if (leave.getLeaveType() == LeaveType.ANNUAL) {
            LeaveState leaveState = approver.approveLeave(numberOfAnnualLeavesLeft, leave.getNumberOfLeaves());
            leave.setLeaveState(leaveState);
            if (leaveState == LeaveState.PARTIALLY_APPROVED) {
                leave.setNumberOfLeaves(numberOfAnnualLeavesLeft);
                updateNoOfLeavesLeft(numberOfAnnualLeavesLeft);
            } else {
                updateNoOfLeavesLeft(leave.getNumberOfLeaves());
            }
            leavesHistory.add(leave);
            return leaveState;
        }
        return LeaveState.APPROVED;
    }

    List<Leave> getLeavesHistory() {
        return leavesHistory;
    }
}
