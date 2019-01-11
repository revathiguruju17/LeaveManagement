class EmployeeAuthentication {

    static boolean checkValidApprover(Employee approver) {
        return approver.getClass() == Approver.class;
    }
}
