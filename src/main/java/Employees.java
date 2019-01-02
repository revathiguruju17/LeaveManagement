import java.util.ArrayList;
import java.util.List;

class Employees {
    private List<Employee> employees;

    Employees() {
        employees = new ArrayList<>();
    }

    void addEmployee(Employee employee) {
        employees.add(employee);
    }

    boolean checkID(String id) {
        for (Employee employee:employees) {
            if(employee.compareID(id)){
                return true;
            }
        }
        return false;
    }

    boolean checkPassword(String password) {
        for (Employee employee:employees) {
            if(employee.comparePassword(password)){
                return true;
            }
        }
        return false;
    }

    boolean contains(Employee employeeMock) {
        return employees.contains(employeeMock);
    }
}
