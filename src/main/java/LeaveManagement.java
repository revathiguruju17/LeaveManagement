
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class LeaveManagement {
    private List<Leave> leaveHistory;
    private List<Leave> leaveRequests;

    LeaveManagement() {
        leaveHistory = new ArrayList<>();
        leaveRequests = new ArrayList<>();
    }

    void applyLeave(int employeeID, String password, Organization organization, Leave leave) {
        Employee employee = organization.getEmployee(employeeID);
        employee.login(password);
        boolean isDateValid = DateValidator.checkWhetherTheLeaveAppliedIsForFutureOrNot(leave.getStartDate(), leave.getEndDate());
        if (!isDateValid) {
            throw new DateInvalidException("invalid date");
        }
        leave.setEmployeeID(employeeID);
        leaveRequests.add(leave);
    }

    void validateLeaveRequests(int approverID, String password, Organization organization) throws IllegalAccessException {
        Employee approver = organization.getEmployee(approverID);
        approver.login(password);
        if (!EmployeeAuthentication.checkValidApprover(approver)) {
            throw new IllegalAccessException("user cannot access the data");
        }
        List<Leave> leaves = getLeaveRequestsForThisApprover(approver);
        for (Leave leave : leaves) {
            Employee leaveRequester = organization.getEmployee(leave.getEmployeeID());
            LeaveState leaveState = approver.approveLeave(leaveRequester.getNumberOfLeavesLeft(), leave.getNumberOfLeaves());
            leaveRequester.updateNoOfLeavesLeft(leaveState, leave.getNumberOfLeaves());
            if (leaveState != LeaveState.REJECTED) {
                leaveHistory.add(leave);
            }
            leaveRequests.remove(leave);
        }
    }

    private List<Leave> getLeaveRequestsForThisApprover(Employee approver) {
        List<Leave> leaves = new ArrayList<>();
        List<Integer> employees = approver.getEmployeesOfTheApprover();
        for (Leave leave : leaveRequests) {
            for (int employeeID : employees) {
                if (leave.getEmployeeID() == employeeID) {
                    leaves.add(leave);
                }
            }
        }
        return leaves;
    }

    List<Leave> getLeaveHistoryOfEmployeeBasedOnTheDate(Date date, int ID, String password, Organization organization) throws IllegalAccessException {
        Employee employee = organization.getEmployee(ID);
        if (!EmployeeAuthentication.checkValidApprover(employee)) {
            throw new IllegalAccessException();
        }
        employee.login(password);
        List<Leave> leaves = new ArrayList<>();
        for (Leave leave : leaveHistory) {
            if (date.compareTo(leave.getStartDate()) >= 0 && leave.getEndDate().compareTo(date) >= 0) {
                leaves.add(leave);
            }
        }
        return leaves;
    }

    List<Leave> getLeaveHistoryOfEmployee(int employeeID, String password, Organization organization) {
        Employee employee = organization.getEmployee(employeeID);
        employee.login(password);
        List<Leave> leaves = new ArrayList<>();
        for (Leave leave : leaveHistory) {
            if (leave.getEmployeeID() == employeeID) {
                leaves.add(leave);
            }
        }
        return leaves;
    }

    boolean checkLeaveRequests(Leave leave) {
        return leaveRequests.contains(leave);
    }

    boolean checkLeaveHistory(Leave leave) {
        return leaveHistory.contains(leave);

    }
}
