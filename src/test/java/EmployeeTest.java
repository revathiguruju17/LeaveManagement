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
        approver = new Approver("id","password");
    }

    @Test
    void shouldChangeTheStateOfEmployeeWhenLogin() {
        employee.login("123456");
        assertEquals(EmployeeState.LOGIN, employee.getState());
    }

    @Test
    void shouldChangeTheStateOfEmployeeWhenLogout() {
        employee.logout();
        assertEquals(EmployeeState.LOGOUT, employee.getState());
    }

    @Test
    void shouldReturnSuccessfulMessageIfTheApproverAcceptsTheAnnualLeave() {
        Date startDate = new Date(2018, 12, 21);
        Date endDate = new Date(2018, 12, 22);
        Leave leave = new Leave(2, startDate, endDate, LeaveType.ANNUAL);
        LeaveState leaveState = employee.applyLeave(leave, approver);
        assertEquals(LeaveState.APPROVED, leaveState);
    }

    @Test
    void shouldReturnCorrectMessageIfTheApproverRejectsTheAnnualLeave() {
        Date startDate = new Date(2018, 12, 1);
        Date endDate = new Date(2018, 12, 15);
        Leave leave = new Leave(15, startDate, endDate, LeaveType.ANNUAL);
        LeaveState leaveState = employee.applyLeave(leave, approver);
        assertEquals(LeaveState.PARTIALLY_APPROVED, leaveState);
    }

    @Test
    void shouldNotReduceTheNumberOfLeavesIfTheLeaveIsSickLeave() {
        Date startDate = new Date(2018, 12, 21);
        Date endDate = new Date(2018, 12, 22);
        int leavesLeftBeforeApplyingForLeave = employee.getNumberOfAnnualLeavesLeft();
        Leave leave = new Leave(2, startDate, endDate, LeaveType.SICK);
        LeaveState leaveState = employee.applyLeave(leave, approver);
        int leavesLeftAfterApplyingForLeave = employee.getNumberOfAnnualLeavesLeft();
        assertEquals(LeaveState.APPROVED, leaveState);
        assertEquals(leavesLeftAfterApplyingForLeave, leavesLeftBeforeApplyingForLeave);
    }
}
