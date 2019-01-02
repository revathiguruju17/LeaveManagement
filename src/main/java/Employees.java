import java.util.ArrayList;
import java.util.List;

public class Employees {
    private List<Employee> employees;

    public Employees() {
        employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public boolean checkID(String id) {
        for (Employee employee:employees) {
            if(employee.compareID(id)){
                return true;
            }
        }
        return false;
    }

    public boolean checkPassword(String password) {
        for (Employee employee:employees) {
            if(employee.comparePassword(password)){
                return true;
            }
        }
        return false;
    }
}
