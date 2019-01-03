import java.util.ArrayList;
import java.util.List;

public class Organization {
    private List<Employee> employees;

    Organization() {
        employees = new ArrayList<>();
    }

    void addEmployee(Employee employee) {
        employees.add(employee);
    }

    boolean checkID(String id) {
        for (Employee employee : employees) {
            if (employee.compareID(id)) {
                return true;
            }
        }
        return false;
    }

    boolean checkPassword(String password) {
        for (Employee employee : employees) {
            if (employee.comparePassword(password)) {
                return true;
            }
        }
        return false;
    }

    boolean contains(Employee employee) {
        return employees.contains(employee);
    }

    public Employee getEmployee() {
        for (Employee employee : employees) {
            if (employee.getState() == EmployeeState.LOGIN) {
                return employee;
            }
        }
        return null;
    }

    public void addEmployeesToTheList(Organization organization) {
        Employee employee1 = new Employee("id1", "password1");
        Employee employee2 = new Employee("id2", "password2");
        Employee employee3 = new Employee("id3", "password3");
        organization.addEmployee(employee1);
        organization.addEmployee(employee2);
        organization.addEmployee(employee3);
    }
}
