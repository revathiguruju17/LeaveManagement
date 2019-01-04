import java.util.List;

class LeaveManagement {

    LeaveState applyLeave(String id, String password, Leave leave, Organization organization, Approver approver) {
        if (organization.employeeLogin(id, password)) {
            Employee employee = organization.getEmployee(id);
            return employee.applyLeave(leave, approver);
        }
        throw new LoginInvalidException("invalid userID and password");
    }

    List<Leave> getEmployeeLeaveHistory(String id, String password, Organization organization) {
        if (organization.employeeLogin(id, password)) {
            Employee employee = organization.getEmployee(id);
            return employee.getLeavesHistory();
        }
        throw new LoginInvalidException("invalid userID and password");
    }
}
