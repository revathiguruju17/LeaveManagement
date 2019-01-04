import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LeaveManagementTest {
    private LeaveManagement leaveManagement;
    private Organization organization;
    private Approver approver;

    @BeforeEach
    void init() {
        leaveManagement = new LeaveManagement();
        organization = new Organization();
        approver = new Approver("id","password");
        Employee employee = new Employee("id1", "password1");
        organization.addEmployee(employee);
    }

    @Test
    void shouldReturnLeaveStateAsApprovedIfTheApproverApprovesTheLeave() {
        Date startDate = new Date(2018, 12, 2);
        Date endDate = new Date(2018, 12, 3);
        Leave leave = new Leave(2, startDate, endDate, LeaveType.ANNUAL);
        LeaveState leaveState = leaveManagement.applyLeave("id1", "password1", leave, organization, approver);
        assertEquals(LeaveState.APPROVED, leaveState);
    }

    @Test
    void shouldReturnInvalidIDExceptionIfTheUserIDAndPasswordIsInvalid() {
        Date startDate = new Date(2018, 12, 2);
        Date endDate = new Date(2018, 12, 3);
        Leave leave = new Leave(2, startDate, endDate, LeaveType.ANNUAL);
        assertThrows(LoginInvalidException.class, () -> leaveManagement.applyLeave("id", "password", leave, organization, approver));
    }

    @Test
    void shouldReturnLeaveStateAsPartiallyApprovedIfTheAnnualLeavesAreLessThanAppliedLeaves() {
        Date startDate = new Date(2018, 12, 2);
        Date endDate = new Date(2018, 12, 16);
        Leave leave = new Leave(15, startDate, endDate, LeaveType.ANNUAL);
        LeaveState leaveState = leaveManagement.applyLeave("id1", "password1", leave, organization, approver);
        assertEquals(LeaveState.PARTIALLY_APPROVED, leaveState);
    }


    @Test
    void shouldReturnEmptyListIfTheEmployeeDoesNotApplyForALeave(){
        List<Leave> leaves = leaveManagement.getEmployeeLeaveHistory("id1", "password1", organization);
        assertTrue(leaves.isEmpty());
    }

    @Test
    void shouldReturnLeaveHistoryWhenEmployeeEnterValidID() {
        Date startDate = new Date(2018, 12, 2);
        Date endDate = new Date(2018, 12, 3);
        Leave leave = new Leave(2, startDate, endDate, LeaveType.ANNUAL);
        leaveManagement.applyLeave("id1", "password1", leave, organization, approver);
        List<Leave> leaves = leaveManagement.getEmployeeLeaveHistory("id1", "password1", organization);
        assertTrue(leaves.contains(leave));
    }
}
