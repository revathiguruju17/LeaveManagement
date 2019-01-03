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
        assertTrue(employee.compareUserIDAndPassword("revathi","123456"));
    }

    @Test
    void shouldReturnFalseForInValidUserIDAndPassword() {
        assertFalse(employee.compareUserIDAndPassword("lavanya","123456"));
    }

    @Test
    void shouldUpdateTheEmployeeLeaveDetailsWhenEmployeeAppliesForAnnualLeave(){
        Date startDate = new Date(2018,12,21);
        Date endDate = new Date(2018,12,22);
        Leave leave = new Leave(2,startDate,endDate,LeaveType.ANNUAL);
        employee.applyLeave(leave);
        assertTrue(employee.checkLeaveApplied(leave));
    }

    @Test
    void shouldNotReduceTheNumberOfLeavesIfTheLeaveIsSickLeave(){
        Date startDate = new Date(2018,12,21);
        Date endDate = new Date(2018,12,22);
        Leave leave = new Leave(2,startDate,endDate,LeaveType.SICK);
        int numberOfLeavesLeftBefore = employee.getNumberOfAnnualLeavesLeft();
        employee.applyLeave(leave);
        int numberOfLeavesLeftAfter = employee.getNumberOfAnnualLeavesLeft();
        assertEquals(numberOfLeavesLeftBefore,numberOfLeavesLeftAfter);
    }
}
