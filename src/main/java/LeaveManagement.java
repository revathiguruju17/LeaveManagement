import java.util.List;

class LeaveManagement {

    LeaveState applyLeave(int id, String password, Leave leave, Organization organization) {
        Employee employee = organization.getEmployee(id);
        employee.login(password);
        if(DateValidator.checkWhetherTheLeaveAppliedIsForFutureOrNot(leave.getStartDate(), leave.getEndDate())) {
            Approver approver = organization.getApprover(employee.getApproverID());
            LeaveState leaveState = approver.approveLeave(employee.getNumberOfAnnualLeavesLeft(), leave.getNumberOfLeaves());
            employee.updateNoOfLeavesLeft(leaveState, leave.getNumberOfLeaves());
            if (leaveState != LeaveState.REJECTED) {
                leave.setEmployeeID(id);
                approver.addLeaveRequest(leave);
                return leaveState;
            }
            return LeaveState.REJECTED;
        }
        throw  new DateInvalidException("date is invalid");
    }

    List<Leave> getEmployeeLeaveHistory(int id, String password, Organization organization) {
        Employee employee = organization.getEmployee(id);
        employee.login(password);
        return employee.getLeavesHistory();
    }
}
