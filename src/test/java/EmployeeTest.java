import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

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
    void shouldChangeTheEmployeeStateWhenUserLoginAndLogout() {
        employee.login();
        assertEquals(EmployeeState.LOGIN, employee.getState());
        employee.logout();
        assertEquals(EmployeeState.LOGOUT, employee.getState());
    }
    
    @Test
    void shouldUpdateTheEmployeeLeaveDetailsWhenEmployeeAppliesForALeave(){
        Date startDate = new Date(2018,12,21);
        Date endDate = new Date(2018,12,22);
        Leave leave = new Leave(2,startDate,endDate,LeaveType.ANNUAL);
        employee.applyLeave(leave);
        assertTrue(employee.checkLeaveApplied(leave));
    }
}
