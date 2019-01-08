import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LeaveManagementTest {
    private LeaveManagement leaveManagement;
    private Organization organization;
    private Approver approver;
    private SimpleDateFormat simpleDateFormat;

    @BeforeEach
    void init() {
        simpleDateFormat = new SimpleDateFormat("dd-mm-yyyy");
        leaveManagement = new LeaveManagement();
        organization = new Organization();
        Employee employee = new Employee(1, "password1", 2);
        approver = new Approver(2, "password2", 2);
        organization.addEmployee(employee);
        organization.addApprover(approver);
    }

    @Test
    void shouldReturnLeaveStateAsApprovedIfTheApproverApprovesTheLeave() throws ParseException {
        Date startDate = simpleDateFormat.parse("02-12-2018");
        Date endDate = simpleDateFormat.parse("03-12-2018");
        Leave leave = new Leave(2, startDate, endDate, LeaveType.ANNUAL);
        LeaveState leaveState = leaveManagement.applyLeave(1, "password1", leave, organization);
        assertEquals(LeaveState.APPROVED, leaveState);
    }

    @Test
    void shouldReturnInvalidIDExceptionIfTheUserIDAndPasswordIsInvalid() throws ParseException {
        Date startDate = simpleDateFormat.parse("02-12-2018");
        Date endDate = simpleDateFormat.parse("03-12-2018");
        Leave leave = new Leave(2, startDate, endDate, LeaveType.ANNUAL);
        assertThrows(LoginInvalidException.class, () -> leaveManagement.applyLeave(2, "password", leave, organization));
    }

    @Test
    void shouldReturnLeaveStateAsPartiallyApprovedIfTheAnnualLeavesAreLessThanAppliedLeaves() throws ParseException{
        Date startDate = simpleDateFormat.parse("02-12-2018");
        Date endDate = simpleDateFormat.parse("16-12-2018");
        Leave leave = new Leave(15, startDate, endDate, LeaveType.ANNUAL);
        LeaveState leaveState = leaveManagement.applyLeave(1, "password1", leave, organization);
        assertEquals(LeaveState.PARTIALLY_APPROVED, leaveState);
    }


    @Test
    void shouldReturnEmptyListIfTheEmployeeDoesNotApplyForALeave() {
        List<Leave> leaves = leaveManagement.getEmployeeLeaveHistory(1, "password1", organization);
        assertTrue(leaves.isEmpty());
    }

    @Test
    void shouldReturnLeaveHistoryWhenEmployeeEnterValidID() throws ParseException{
        Date startDate = simpleDateFormat.parse("02-12-2018");
        Date endDate = simpleDateFormat.parse("03-12-2018");
        Leave leave = new Leave(2, startDate, endDate, LeaveType.ANNUAL);
        leaveManagement.applyLeave(1, "password1", leave, organization);
        List<Leave> leaves = leaveManagement.getEmployeeLeaveHistory(1, "password1", organization);
        assertTrue(leaves.contains(leave));
    }
}
