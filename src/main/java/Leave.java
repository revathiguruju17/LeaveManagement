import java.util.Date;

public class Leave {
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

    public LeaveType getLeaveType() {
        return leaveType;
    }
}
