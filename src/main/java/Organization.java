import java.util.ArrayList;
import java.util.List;

class Organization {
    private List<Employee> employees;
    private List<Approver> approvers;


    Organization() {
        employees = new ArrayList<>();
    }

    void addEmployee(Employee employee) {
        this.employees.add(employee);
    }

    Approver getApprover(String id){
        for (Approver approver : approvers) {
            if (id.equals(approver.getID())) {
                return approver;
            }
        }
        throw  new LoginInvalidException("ID is invalid");
    }

    Employee getEmployee(String id) {
        for (Employee employee : this.employees) {
            if (id.equals(employee.getID())) {
                return employee;
            }
        }
        throw  new LoginInvalidException("Id is invalid");
    }
}
