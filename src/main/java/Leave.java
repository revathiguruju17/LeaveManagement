import java.util.Date;

class Leave {
    private String employeeID;
    private Date startDate;
    private Date endDate;
    private int NumberOfLeaves;
    private LeaveState leaveState;
    private LeaveType leaveType;

    Leave(int NumberOfLeaves, Date startDate, Date endDate, LeaveType leaveType) {
        this.NumberOfLeaves = NumberOfLeaves;
        this.startDate = startDate;
        this.endDate = endDate;
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

    Date getStartDate() {
        return startDate;
    }

    Date getEndDate() {
        return endDate;
    }
}
