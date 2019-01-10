import java.util.Date;
import java.util.List;

class LeaveManagement {
    List<Leave> getAnEmployeeLeaveHistory(int id, String password, Organization organization) {
        Employee employee = organization.getEmployee(id);
        employee.login(password);
        return employee.getLeavesHistory();
    }

    void applyLeave(int employeeID, String password, Organization organization, Leave leave) {
        Employee employee = organization.getEmployee(employeeID);
        employee.login(password);
        boolean isDateValid = DateValidator.checkWhetherTheLeaveAppliedIsForFutureOrNot(leave.getStartDate(), leave.getEndDate());
        if (!isDateValid) {
            throw new DateInvalidException("invalid date");
        }
        Employee approver = organization.getApprover(employee);
        leave.setEmployeeID(employeeID);
        approver.addLeaveRequest(leave);
    }

    List<Leave> getAllEmployeesLeaveHistoryBasedOnGivenDate(Date date, Organization organization, int approverID, String password) {
        Employee approver = organization.getEmployee(approverID);
        approver.login(password);
        return organization.getEmployeesLeaveHistoryBasedOnDate(date);
    }

    void validateLeaveRequest(int approverID, String password, Organization organization) {
        Employee approver = organization.getEmployee(approverID);
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
