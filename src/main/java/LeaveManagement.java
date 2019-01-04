class LeaveManagement {

    LeaveState start(String id, String password, Leave leave, Organization organization, Approver approver) {
        if (organization.employeeLogin(id, password)) {
            Employee employee = organization.getEmployee(id);
            return employee.applyLeave(leave, approver);
        }
        throw new LoginInvalidException("invalid userID and password");
    }
}
