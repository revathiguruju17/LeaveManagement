import java.util.List;

class LeaveManagement {

    LeaveState applyLeave(int id, String password, Leave leave, Organization organization) {
        Employee employee = organization.getEmployee(id);
        employee.login(password);
        return employee.applyLeave(leave, organization);
    }

    List<Leave> getEmployeeLeaveHistory(int id, String password, Organization organization) {
        Employee employee = organization.getEmployee(id);
        employee.login(password);
        return employee.getLeavesHistory();
    }
}
