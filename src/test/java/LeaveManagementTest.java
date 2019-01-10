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
    private SimpleDateFormat simpleDateFormat;
    private Leave leave1;
    private Leave leave2;
    private Approver approver1;
    private Employee employee1;

    @BeforeEach
    void init() throws ParseException {
        simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date startDate1 = simpleDateFormat.parse("22-01-2019");
        Date endDate1 = simpleDateFormat.parse("24-01-2019");
        leave1 = new Leave(startDate1, endDate1);
        Date startDate2 = simpleDateFormat.parse("28-01-2019");
        Date endDate2 = simpleDateFormat.parse("29-01-2019");
        leave2 = new Leave(startDate2, endDate2);
        leaveManagement = new LeaveManagement();
        organization = new Organization();
        employee1 = new Employee(1, "password1");
        Employee employee2 = new Employee(2, "password2");
        Employee employee3 = new Employee(3, "password3");
        approver1 = new Approver(4, "password4");
        approver1.addLeaveRequester(1);
        approver1.addLeaveRequester(2);
        approver1.addLeaveRequester(3);
        organization.addEmployee(employee1);
        organization.addEmployee(employee2);
        organization.addEmployee(employee3);
        organization.addEmployee(approver1);
    }

    @Test
    void shouldReturnEmptyListIfTheEmployeeDoesNotApplyForALeave() {
        List<Leave> leaves = leaveManagement.getAnEmployeeLeaveHistory(1, "password1", organization);
        assertTrue(leaves.isEmpty());
    }

    @Test
    void shouldThrowExceptionIfTheUserApplyLeaveWithWrongID() {
        assertThrows(LoginInvalidException.class, () -> leaveManagement.
                applyLeave(6, "password1", organization, leave1));
    }

    @Test
    void shouldReturnAllEmployeeLeavesBasesOnTheGivenDate() throws ParseException {
        leaveManagement.applyLeave(1, "password1", organization, leave1);
        leaveManagement.applyLeave(2, "password2", organization, leave2);
        leaveManagement.applyLeave(3, "password3", organization, leave2);
        leaveManagement.validateLeaveRequest(4, "password4", organization);
        List<Leave> leaves = leaveManagement.getAllEmployeesLeaveHistoryBasedOnGivenDate
                (simpleDateFormat.parse("23-01-2019"), organization, 4, "password4");
        assertEquals(leave1, leaves.get(0));
    }

    @Test
    void shouldThrowExceptionIfTheApproverGivesWrongPassword() {
        assertThrows(LoginInvalidException.class, () -> leaveManagement.
                validateLeaveRequest(4, "password2", organization));
    }

    @Test
    void shouldAddLeaveRequestsInTheApproverWhenAnEmployeeApplyForALeave() {
        leaveManagement.applyLeave(1, "password1", organization, leave1);
        leaveManagement.applyLeave(2, "password2", organization, leave1);
        leaveManagement.applyLeave(3, "password3", organization, leave1);
        List<Leave> leaveRequests = approver1.getLeaveRequests();
        assertEquals(leave1, leaveRequests.get(0));
        assertEquals(leave1, leaveRequests.get(1));
        assertEquals(leave1, leaveRequests.get(2));
    }

    @Test
    void shouldThrowExceptionIfTheEmployeeGivesInvalidDateWhileApplyingLeave() throws ParseException {
        Date startDate = simpleDateFormat.parse("01-01-2019");
        Date endDate = simpleDateFormat.parse("05-01-2019");
        Leave leave = new Leave(startDate, endDate);
        assertThrows(DateInvalidException.class, () -> leaveManagement.
                applyLeave(1, "password1", organization, leave));
    }

    @Test
    void shouldDeleteTheLeaveRequestInApproverObjectWhenApproverVisitsThatLeaveRequest() {
        leaveManagement.applyLeave(1, "password1", organization, leave1);
        leaveManagement.validateLeaveRequest(4, "password4", organization);
        List<Leave> leaveRequests = approver1.getLeaveRequests();
        assertTrue(leaveRequests.isEmpty());
    }

    @Test
    void shouldNotAddLeaveInEmployeeLeaveHistoryUntilApproverVisitsTheLeaveRequest() {
        leaveManagement.applyLeave(1, "password1", organization, leave1);
        List<Leave> leaves = employee1.getLeavesHistory();
        assertFalse(leaves.contains(leave1));
    }

    @Test
    void shouldAddLeaveInEmployeeLeaveHistoryWhenApproverApprovesTheLeaveRequest() {
        leaveManagement.applyLeave(1, "password1", organization, leave1);
        leaveManagement.validateLeaveRequest(4, "password4", organization);
        List<Leave> leaveHistory = employee1.getLeavesHistory();
        assertTrue(leaveHistory.contains(leave1));
    }
}
