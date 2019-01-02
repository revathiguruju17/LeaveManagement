import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmployeeTest {
    private Employee employee;
    @BeforeEach
    void init(){
        employee = new Employee();
        employee.setID("revathi");
        employee.setPassword("123456");
    }

    @Test
    void shouldReturnTrueForValidUserID(){
        assertTrue(employee.compareID("revathi"));
    }

    @Test
    void shouldReturnFalseForInValidUserID(){
        assertFalse(employee.compareID("lavanya"));
    }

    @Test
    void shouldReturnTrueForValidPassword(){
        assertTrue(employee.comparePassword("123456"));
    }

    @Test
    void shouldReturnFalseForInValidPassword(){
        assertFalse(employee.comparePassword("3333333"));
    }
}
