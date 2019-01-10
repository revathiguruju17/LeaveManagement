import java.util.ArrayList;
import java.util.List;

class Approver extends Employee {

    private List<Leave> leaveRequests;
    private List<Employee> leaveAppliers;

    Approver(int ID, String password) {
        super(ID, password);
        this.leaveRequests = new ArrayList<>();
        this.leaveAppliers = new ArrayList<>();
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

    @Override
    public void addLeaveRequest(Leave leave) { leaveRequests.add(leave); }

    @Override
    public void addLeaveRequester(Employee employee) {
        leaveAppliers.add(employee);
    }

    @Override
    public List<Leave> getLeaveRequests() {
        return leaveRequests;
    }

    @Override
    boolean checkLeaveRequester(Employee employee) {
        return this.leaveAppliers.contains(employee);
    }
}
