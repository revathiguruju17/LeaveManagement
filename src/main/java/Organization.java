import java.util.ArrayList;
import java.util.List;

class Organization {
    private List<Employee> employees;

    Organization() {
        employees = new ArrayList<>();
    }

    void addEmployee(Employee employee) {
        employees.add(employee);
    }

    boolean employeeLogin(String id, String password) {
        for (Employee employee : employees) {
            if (employee.compareUserIDAndPassword(id, password)) {
                Employee employee1 = getEmployee(id);
                employee1.login();
                return true;
            }
        }
        return false;
    }

    Employee getEmployee(String id) {
        for (Employee employee : employees) {
            if (id.equals(employee.getID())) {
                return employee;
            }
        }
        return null;
    }
}
