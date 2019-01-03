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
        this.numberOfAnnualLeavesLeft = 24;
        leavesTaken = new ArrayList<>();
        employeeState = EmployeeState.LOGOUT;
    }

    boolean compareID(String id) {
        return this.ID.equals(id);
    }

    boolean comparePassword(String password) {
        return this.password.equals(password);
    }

    private void updateNoOfLeavesLeft(int numberOfAnnualLeavesTaken) {
        this.numberOfAnnualLeavesLeft -= numberOfAnnualLeavesTaken;
    }

    int getNumberOfAnnualLeavesLeft() {
        return numberOfAnnualLeavesLeft;
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

    void applyLeave(Leave leave) {
        if (leave.getLeaveType() == LeaveType.ANNUAL) {
            int numberOfLeaves = leave.getNumberOfLeaves();
            updateNoOfLeavesLeft(numberOfLeaves);
        }
        leavesTaken.add(leave);
    }

    private void setEmployeeState() {
        if (this.employeeState == EmployeeState.LOGOUT) {
            employeeState = EmployeeState.LOGIN;
        } else {
            employeeState = EmployeeState.LOGOUT;
        }
    }

    boolean checkLeaveApplied(Leave leave) {
        return leavesTaken.contains(leave);
    }
}
