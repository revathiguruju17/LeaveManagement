import java.util.ArrayList;
import java.util.List;

 class Approver extends Employee {

    private List<Leave> leaveRequests;
    private List<Integer> leaveRequesters;

    Approver(int ID, String password) {
        super(ID, password);
        this.leaveRequests = new ArrayList<>();
        this.leaveRequesters = new ArrayList<>();
    }

    @Override
    public LeaveState approveLeave(int numberOfLeavesLeft, int numberOfLeavesWant) {
        if (numberOfLeavesLeft == 0) {
            return LeaveState.REJECTED;
        } else if (numberOfLeavesLeft >= numberOfLeavesWant)
            return LeaveState.APPROVED;
        else {
            return LeaveState.PARTIALLY_APPROVED;
        }
    }

    void addLeaveRequest(Leave leave) {
        leaveRequests.add(leave);
    }

    void addLeaveRequester(int ID) {
        leaveRequesters.add(ID);
    }

    @Override
    public List<Leave> getLeaveRequests() {
        return leaveRequests;
    }

    @Override
    boolean checkLeaveRequester(int ID) {
        for (int requestor : leaveRequesters) {
            if (requestor == ID) {
                return true;
            }
        }
        return false;
    }
}
