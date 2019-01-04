import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LeaveManagementTest {
    private LeaveManagement leaveManagement;
    private Organization organization;
    private Approver approver;

    @BeforeEach
    void init() {
        leaveManagement = new LeaveManagement();
        organization = new Organization();
        approver = new Approver();
        Employee employee = new Employee("id1", "password1");
        organization.addEmployee(employee);
    }

    @Test
    void shouldReturnLeaveStateAsApprovedIfTheLeaveIsApproved() {
        Date startDate = new Date(2018, 12, 2);
        Date endDate = new Date(2018, 12, 3);
        Leave leave = new Leave(2, startDate, endDate, LeaveType.ANNUAL);
        LeaveState leaveState = leaveManagement.start("id1", "password1", leave, organization, approver);
        assertEquals(LeaveState.APPROVED, leaveState);
    }

    @Test
    void shouldReturnInvalidIDMessageIfTheUserIDAndPasswordIsInvalid() {
        Date startDate = new Date(2018, 12, 2);
        Date endDate = new Date(2018, 12, 3);
        Leave leave = new Leave(2, startDate, endDate, LeaveType.ANNUAL);
        assertThrows(LoginInvalidException.class,()->leaveManagement.start("id", "password", leave, organization, approver));
    }

    @Test
    void shouldReturnLeaveStateAsPartiallyApprovedIfTheAnnualLeavesAreLessThanAppliedLeaves() {
        Date startDate = new Date(2018, 12, 2);
        Date endDate = new Date(2018, 12, 16);
        Leave leave = new Leave(15, startDate, endDate, LeaveType.ANNUAL);
        LeaveState leaveState = leaveManagement.start("id1", "password1", leave, organization, approver);
        assertEquals(LeaveState.PARTIALLY_APPROVED, leaveState);
    }
}
