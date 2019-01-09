import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Organization {
    private List<Employee> employees;


    Organization() {
        employees = new ArrayList<>();
    }

    void addEmployee(Employee employee) {
        this.employees.add(employee);
    }

    Employee getEmployee(int id) {
        for (Employee employee : employees) {
            if (employee.checkID(id)) {
                return employee;
            }
        }
        throw new LoginInvalidException("Id is invalid");
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
}