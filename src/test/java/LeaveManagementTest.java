import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void shouldReturnSuccessfulMessageIfTheLeaveIsApproved() {
        Date startDate = new Date(2018, 12, 2);
        Date endDate = new Date(2018, 12, 3);
        Leave leave = new Leave(2, startDate, endDate, LeaveType.ANNUAL);
        String message = leaveManagement.start("id1", "password1", leave, organization, approver);
        assertEquals("leave accepted", message);
    }

    @Test
    void shouldReturnInvalidIDMessageIfTheUserIDAndPasswordIsInvalid() {
        Date startDate = new Date(2018, 12, 2);
        Date endDate = new Date(2018, 12, 3);
        Leave leave = new Leave(2, startDate, endDate, LeaveType.ANNUAL);
        String message = leaveManagement.start("id", "password", leave, organization, approver);
        assertEquals("invalid ID and password", message);
    }

    @Test
    void shouldReturnLeaveRejectedMessageIfTheAnnualLeavesAreNotLeft(){
        Date startDate = new Date(2018, 12, 2);
        Date endDate = new Date(2018, 12, 13);
        Leave leave = new Leave(13, startDate, endDate, LeaveType.ANNUAL);
        String message = leaveManagement.start("id1", "password1", leave, organization, approver);
        assertEquals("leave rejected", message);
    }
}
