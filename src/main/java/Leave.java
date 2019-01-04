import java.util.Date;

class Leave {
    private Date fromDate;
    private Date toDate;
    private int NumberOfLeaves;

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
}
