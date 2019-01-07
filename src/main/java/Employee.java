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
        this.employeeState = EmployeeState.LOGOUT;
        this.numberOfAnnualLeavesLeft = 10;
        leavesHistory = new ArrayList<>();
    }

    int getNumberOfAnnualLeavesLeft() {
        return numberOfAnnualLeavesLeft;
    }

    List<Leave> getLeavesHistory() {
        return leavesHistory;
    }

    String getID() {
        return ID;
    }

    EmployeeState getState() {
        return this.employeeState;
    }

    private void updateNoOfLeavesLeft(int numberOfAnnualLeavesTaken) {
        this.numberOfAnnualLeavesLeft -= numberOfAnnualLeavesTaken;
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

}
