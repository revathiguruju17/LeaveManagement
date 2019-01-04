class LeaveManagement {

    String start(String id, String password, Leave leave, Organization organization, Approver approver) {
        if (organization.employeeLogin(id, password)) {
            Employee employee = organization.getEmployee(id);
            return employee.applyLeave(leave, approver);
        }
        return "invalid ID and password";
    }
}
