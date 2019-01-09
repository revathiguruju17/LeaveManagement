import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Organization {
    private List<Employee> employees;
    private List<Approver> approvers;


    Organization() {
        employees = new ArrayList<>();
        approvers = new ArrayList<>();
    }

    void addEmployee(Employee employee) {
        this.employees.add(employee);
    }

    Approver getApprover(int id) {
        for (Approver approver : approvers) {
            if (approver.checkID(id)) {
                return approver;
            }
        }
        throw new LoginInvalidException("ID is invalid");
    }

    Employee getEmployee(int id) {
        for (Employee employee : employees) {
            if (employee.checkID(id)) {
                return employee;
            }
        }
        throw new LoginInvalidException("Id is invalid");
    }

    void addApprover(Approver approver) {
        approvers.add(approver);
    }

    List<Leave> getEmployeesLeaveHistoryBasedOnDate(Date date) {
        List<Leave> leaves = new ArrayList<>();
        for (Employee employee : employees) {
            List<Leave> employeeLeaves = employee.getLeavesHistory();
            for (Leave leave : employeeLeaves) {
                if (date.compareTo(leave.getStartDate()) >= 0 && leave.getEndDate().compareTo(date) >= 0) {
                    leaves.add(leave);
                }
            }
        }
        return leaves;
    }

    List<Leave> getApproversLeaveHistoryBasedOnDate(Date date) {
        List<Leave> leaves = new ArrayList<>();
        for (Approver approver : approvers) {
            List<Leave> employeeLeaves = approver.getLeavesHistory();
            for (Leave leave : employeeLeaves) {
                if (date.compareTo(leave.getStartDate()) >= 0 && leave.getEndDate().compareTo(date) >= 0) {
                    leaves.add(leave);
                }
            }
        }
        return leaves;
    }
}