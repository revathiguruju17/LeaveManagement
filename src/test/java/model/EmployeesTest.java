package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class EmployeesTest {
    private Employees employees;
    private Employee employeeMock;

    @BeforeEach
    void init(){
       employees = new Employees();
       employeeMock = Mockito.mock(Employee.class);
       employees.addEmployee(employeeMock);
    }

    @Test
    void checkingWhetherTheCompareIDMethodIsCalledOrNot(){
        when(employeeMock.compareID("revathi")).thenReturn(true);
        assertTrue(employees.checkID("revathi"));
    }


    @Test
    void checkingWhetherTheComparePasswordMethodIsCalledOrNot(){
        when(employeeMock.comparePassword("123456")).thenReturn(true);
        assertTrue(employees.checkPassword("123456"));
    }

    @Test
    void shouldAddTheNewEmployeeToTheListCorrectly(){
        assertTrue(employees.contains(employeeMock));
    }
}
