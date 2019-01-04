import java.util.ArrayList;
import java.util.List;

class Employee {

    private String ID;
    private String password;
    private EmployeeState employeeState;
    private int numberOfAnnualLeavesLeft;
    private List<Leave> leavesTaken;

    Employee(String ID, String password) {
        this.ID = ID;
        this.password = password;
        this.numberOfAnnualLeavesLeft = 10;
        leavesTaken = new ArrayList<>();
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
        setEmployeeState();
    }

    void logout() {
        setEmployeeState();
    }

    String applyLeave(Leave leave, Approver approver) {
        if (leave.getLeaveType() == LeaveType.ANNUAL) {
            String message = approver.approveLeave(numberOfAnnualLeavesLeft, leave.getNumberOfLeaves());
            updateNoOfLeavesLeft(leave.getNumberOfLeaves());
            leavesTaken.add(leave);
            return message;
        }
        return "leave approved";
    }

    private void setEmployeeState() {
        if (this.employeeState == EmployeeState.LOGOUT) {
            employeeState = EmployeeState.LOGIN;
        } else {
            employeeState = EmployeeState.LOGOUT;
        }
    }
}
