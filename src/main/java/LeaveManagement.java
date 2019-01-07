import java.util.List;

class LeaveManagement {

    LeaveState applyLeave(String id, String password, Leave leave, Organization organization, Approver approver) {
        Employee employee = organization.getEmployee(id);
        employee.login(password);
        return employee.applyLeave(leave, approver);
    }

    List<Leave> getEmployeeLeaveHistory(String id, String password, Organization organization) {
        Employee employee = organization.getEmployee(id);
        return employee.getLeavesHistory();
    }
}
