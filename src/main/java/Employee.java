import java.util.ArrayList;
import java.util.List;

class Employee {
    private int ID;
    private String password;
    private int approverID;
    private EmployeeState employeeState;

    private int numberOfAnnualLeavesLeft;
    private List<Leave> leavesHistory;

    Employee(int ID, String password, int approverID) {
        this.ID = ID;
        this.password = password;
        this.approverID = approverID;
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

    int getApproverID() {
        return approverID;
    }

    void addLeaveToLeaveHistory(Leave leave) {
        this.leavesHistory.add(leave);
    }

    void applyLeave(int id, String password, Leave leave, Organization organization) {
        login(password);
        if (DateValidator.checkWhetherTheLeaveAppliedIsForFutureOrNot(leave.getStartDate(), leave.getEndDate())) {
            Approver approver = organization.getApprover(getApproverID());
            leave.setEmployeeID(id);
            approver.addLeaveRequest(leave);
        }
        throw new DateInvalidException("date is invalid");
    }

    int getNumberOfLeavesLeft() {
        return numberOfAnnualLeavesLeft;
    }
}
