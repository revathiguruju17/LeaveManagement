import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class EmployeesTest {
    private Employees employees;
    private Employee employeeMock;

    @BeforeEach
    void init(){
       employees = new Employees();
       employeeMock = Mockito.mock(Employee.class);
       employeeMock.setID("revathi");
       employeeMock.setPassword("123456");
       employees.addEmployee(employeeMock);
    }

    @Test
    void shouldReturnTrueWhenUserEnterValidID(){
        when(employeeMock.compareID("revathi")).thenReturn(true);
        assertTrue(employees.checkID("revathi"));
    }

    @Test
    void shouldReturnFalseWhenUserEnterInValidID(){
        when(employeeMock.compareID("123revat")).thenReturn(false);
        assertFalse(employees.checkID("123revat"));
    }

    @Test
    void shouldReturnTrueWhenUserEnterValidPassword(){
        when(employeeMock.comparePassword("123456")).thenReturn(true);
        assertTrue(employees.checkPassword("123456"));
    }

    @Test
    void shouldReturnFalseWhenUserEnterInValidPassword(){
        when(employeeMock.comparePassword("99999")).thenReturn(false);
        assertFalse(employees.checkPassword("99999"));
    }
}
