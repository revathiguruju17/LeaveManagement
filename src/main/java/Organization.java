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

    void employeeLogin(String id, String password){
        boolean loginState = false;
        for (Employee employee : employees) {
            if (employee.compareUserIDAndPassword(id,password)) {
                Employee employee1 = getEmployee(id);
                employee1.login();
                loginState = true;
                break;
            }
        }
        if(!loginState){
            throw new LoginInvalidException("Invalid Login");
        }
    }

    private Employee getEmployee(String id) {
        for (Employee employee : employees) {
            if (id.equals(employee.getID())) {
                return employee;
            }
        }
        return null;
    }
}
