import java.util.Date;
import java.util.List;

class LeaveManagement {
    List<Leave> getAnEmployeeLeaveHistory(int id, String password, Organization organization) {
        Employee employee = organization.getEmployee(id);
        employee.login(password);
        return employee.getLeavesHistory();
    }

    List<Leave> getAllEmployeesLeaveHistoryBasedOnGivenDate(Date date, Organization organization, int approverID, String password) {
        Approver approver = organization.getApprover(approverID);
        approver.login(password);
        return organization.getEmployeesLeaveHistoryBasedOnDate(date);
    }

    void applyLeave(int employeeID, String password, Organization organization, Leave leave) {
        Employee employee = organization.getEmployee(employeeID);
        employee.login(password);
        boolean isDateValid = DateValidator.checkWhetherTheLeaveAppliedIsForFutureOrNot(leave.getStartDate(), leave.getEndDate());
        if (!isDateValid) {
            throw new DateInvalidException("invalid date");
        }
        Approver approver = organization.getApprover(employee.getApproverID());
        leave.setEmployeeID(employeeID);
        approver.addLeaveRequest(leave);
    }

    void validateLeaveRequest(int approverID, String password, Organization organization) {
        Approver approver = organization.getApprover(approverID);
        approver.login(password);
        List<Leave> leaveRequests = approver.getLeaveRequests();
        while (leaveRequests.size() != 0) {
            Leave leave = leaveRequests.get(0);
            Employee employee = organization.getEmployee(leave.getEmployeeID());
            LeaveState leaveState = approver.approveLeave(employee.getNumberOfLeavesLeft(), leave.getNumberOfLeaves());
            employee.updateNoOfLeavesLeft(leaveState, leave.getNumberOfLeaves());
            employee.addLeaveToLeaveHistory(leave);
            leaveRequests.remove(leave);
        }
    }
}
