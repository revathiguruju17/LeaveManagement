import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {
    private Employee employee;
    private Approver approver;

    @BeforeEach
    void init() {
        employee = new Employee("revathi", "123456");
        approver = new Approver();
    }

    @Test
    void shouldChangeTheStateOfEmployeeWhenLogin(){
        employee.login();
        assertEquals(EmployeeState.LOGIN,employee.getState());
    }

    @Test
    void shouldChangeTheStateOfEmployeeWhenLogout(){
        employee.login();
        employee.logout();
        assertEquals(EmployeeState.LOGOUT,employee.getState());
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
    void shouldReturnSuccessfulMessageIfTheApproverAcceptsTheAnnualLeave(){
        Date startDate = new Date(2018,12,21);
        Date endDate = new Date(2018,12,22);
        Leave leave = new Leave(2,startDate,endDate,LeaveType.ANNUAL);
        String message = employee.applyLeave(leave,approver);
        assertEquals("leave approved", message);
    }

    @Test
    void shouldReturnCorrectMessageIfTheApproverRejectsTheAnnualLeave(){
        Date startDate = new Date(2018,12,1);
        Date endDate = new Date(2018,12,15);
        Leave leave = new Leave(15,startDate,endDate,LeaveType.ANNUAL);
        String message = employee.applyLeave(leave,approver);
        assertEquals("only 10 leaves are approved", message);
    }

    @Test
    void shouldNotReduceTheNumberOfLeavesIfTheLeaveIsSickLeave() {
        Date startDate = new Date(2018, 12, 21);
        Date endDate = new Date(2018, 12, 22);
        int leavesLeftBeforeApplyingForLeave = employee.getNumberOfAnnualLeavesLeft();
        Leave leave = new Leave(2, startDate, endDate, LeaveType.SICK);
        String result = employee.applyLeave(leave, approver);
        int leavesLeftAfterApplyingForLeave = employee.getNumberOfAnnualLeavesLeft();
        assertEquals("leave approved", result);
        assertEquals(leavesLeftAfterApplyingForLeave,leavesLeftBeforeApplyingForLeave);
    }
}
