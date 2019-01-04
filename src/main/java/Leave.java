import java.util.Date;

class Leave {
    private String employeeID;
    private Date fromDate;
    private Date toDate;
    private int NumberOfLeaves;
    private LeaveState leaveState;
    private LeaveType leaveType;

    Leave(int NumberOfLeaves, Date fromDate, Date toDate, LeaveType leaveType) {
        this.NumberOfLeaves = NumberOfLeaves;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.leaveType = leaveType;
    }

    int getNumberOfLeaves() {
        return NumberOfLeaves;
    }

    LeaveType getLeaveType() {
        return leaveType;
    }

    void setLeaveState(LeaveState leaveState) {
        this.leaveState = leaveState;
    }

    void setNumberOfLeaves(int numberOfLeaves) {
        NumberOfLeaves = numberOfLeaves;
    }

    public void setID() {
        this.employeeID = employeeID;
    }
}
