import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class EmployeeTest {
    private Employee employee;
    private Approver approver;
    private Organization organization;
    private Date startDate;
    private Date endDate;

    @BeforeEach
    void init() {
        employee = new Employee(1, "password1", 3);
        organization = Mockito.mock(Organization.class);
        approver = Mockito.mock(Approver.class);
        organization.addApprover(approver);
        startDate = Mockito.mock(Date.class);
        endDate = Mockito.mock(Date.class);
    }

    @Test
    void shouldChangeTheStateOfEmployeeWhenLogin() {
        employee.login("password1");
        assertEquals(EmployeeState.LOGIN, employee.getState());
    }

    @Test
    void shouldChangeTheStateOfEmployeeWhenLogout() {
        employee.logout();
        assertEquals(EmployeeState.LOGOUT, employee.getState());
    }

    @Test
    void shouldReturnSuccessfulMessageIfTheApproverAcceptsTheAnnualLeave() {
        Leave leave = new Leave(2, startDate, endDate, LeaveType.ANNUAL);
        when(organization.getApprover(3)).thenReturn(approver);
        when(approver.approveLeave(10, 2)).thenReturn(LeaveState.APPROVED);
        LeaveState leaveState = employee.applyLeave(leave, organization);
        assertEquals(LeaveState.APPROVED, leaveState);
    }

    @Test
    void shouldReturnCorrectMessageIfTheApproverRejectsTheAnnualLeave() {
        Leave leave = new Leave(15, startDate, endDate, LeaveType.ANNUAL);
        when(organization.getApprover(3)).thenReturn(approver);
        when(approver.approveLeave(10, 15)).thenReturn(LeaveState.PARTIALLY_APPROVED);
        LeaveState leaveState = employee.applyLeave(leave, organization);
        assertEquals(LeaveState.PARTIALLY_APPROVED, leaveState);
    }

    @Test
    void shouldNotReduceTheNumberOfLeavesIfTheLeaveIsSickLeave() {
        int leavesLeftBeforeApplyingForLeave = employee.getNumberOfAnnualLeavesLeft();
        Leave leave = new Leave(2, startDate, endDate, LeaveType.SICK);
        LeaveState leaveState = employee.applyLeave(leave, organization);
        int leavesLeftAfterApplyingForLeave = employee.getNumberOfAnnualLeavesLeft();
        assertEquals(LeaveState.APPROVED, leaveState);
        assertEquals(leavesLeftAfterApplyingForLeave, leavesLeftBeforeApplyingForLeave);
    }
}
