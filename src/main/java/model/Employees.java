package model;

import java.util.ArrayList;
import java.util.List;

public class Employees {
    private List<Employee> employees;

    public Employees() {
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

    boolean contains(Employee employeeMock) {
        return employees.contains(employeeMock);
    }

    void updateState(String id) {
        for (Employee employee : employees) {
            if (employee.compareID(id)) {
                employee.setEmployeeState();
            }
        }
    }

    public Employee getEmployee() {
        for (Employee employee : employees) {
            if (employee.getState() == EmployeeState.LOGIN) {
                return employee;
            }
        }
        return null;
    }

    public void addEmployeesToTheList(Employees employees) {
        Employee employee1 = new Employee("id1", "password1");
        Employee employee2 = new Employee("id2", "password2");
        Employee employee3 = new Employee("id3", "password3");
        employees.addEmployee(employee1);
        employees.addEmployee(employee2);
        employees.addEmployee(employee3);
    }
}
