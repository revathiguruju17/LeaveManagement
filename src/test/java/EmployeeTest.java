import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {
    private Employee employee;

    @BeforeEach
    void init() {
        employee = new Employee("revathi", "123456");
    }

    @Test
    void shouldReturnTrueForValidUserID() {
        assertTrue(employee.compareID("revathi"));
    }

    @Test
    void shouldReturnFalseForInValidUserID() {
        assertFalse(employee.compareID("lavanya"));
    }

    @Test
    void shouldReturnTrueForValidPassword() {
        assertTrue(employee.comparePassword("123456"));
    }

    @Test
    void shouldReturnFalseForInValidPassword() {
        assertFalse(employee.comparePassword("3333333"));
    }

    @Test
    void shouldUpdateNoOfLeavesLeftIfAnEmployeeApplyForALeave() {
        employee.updateNoOfLeaves(2);
        assertEquals(22, employee.getNumberOfAnnualLeavesLeft());
    }

    @Test
    void shouldChangeTheEmployeeStateWhenUserLoginAndLogout() {
        employee.login();
        assertEquals(EmployeeState.LOGIN, employee.getState());
        employee.logout();
        assertEquals(EmployeeState.LOGOUT, employee.getState());
    }
}
