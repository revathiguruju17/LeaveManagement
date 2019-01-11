import java.util.ArrayList;
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
}