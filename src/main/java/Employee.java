import java.util.ArrayList;
import java.util.List;

class Employee {
    private int ID;
    private String password;
    private int approver;
    private EmployeeState employeeState;
    private int numberOfAnnualLeavesLeft;
    private List<Leave> leavesHistory;

    Employee(int ID, String password, int approver) {
        this.ID = ID;
        this.password = password;
        this.approver = approver;
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

    int getID() {
        return ID;
    }

    EmployeeState getState() {
        return this.employeeState;
    }

    public int getApprover() {
        return approver;
    }

    boolean checkID(int id) {
        return id == this.ID;
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

    LeaveState applyLeave(Leave leave, Organization organization) {
        if (leave.getLeaveType() == LeaveType.ANNUAL) {
            Approver approver = organization.getApprover(this.approver);
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
