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

    public boolean checkIDAndPassword(String id, String password) {
        for (Employee employee:employees) {
            if(employee.compareIDAndPassword(id, password)){
                return true;
            }
        }
        return false;
    }
}
