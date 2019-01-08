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
    private Leave leave1;
    private Approver approver;
    private Employee employee1;

    @BeforeEach
    void init() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date startDate1 = simpleDateFormat.parse("22-01-2019");
        Date endDate1 = simpleDateFormat.parse("24-01-2019");
        leave1 = new Leave(startDate1, endDate1);
        leaveManagement = new LeaveManagement();
        organization = new Organization();
        employee1 = new Employee(1, "password1", 4);
        Employee employee2 = new Employee(2, "password2", 4);
        Employee employee3 = new Employee(3, "password3", 4);
        approver = new Approver(4, "password4", 4);
        organization.addEmployee(employee1);
        organization.addEmployee(employee2);
        organization.addEmployee(employee3);
        organization.addApprover(approver);
    }


    @Test
    void shouldReturnEmptyListIfTheEmployeeDoesNotApplyForALeave() {
        List<Leave> leaves = leaveManagement.getAnEmployeeLeaveHistory(1, "password1", organization);
        assertTrue(leaves.isEmpty());
    }

    @Test
    void shouldAddLeaveRequestsInTheApproverWhenAnEmployeeApplyForALeave() {
        leaveManagement.applyLeave(1, "password1", organization, leave1);
        leaveManagement.applyLeave(2, "password2", organization, leave1);
        leaveManagement.applyLeave(3, "password3", organization, leave1);
        List<Leave> leaveRequests = approver.getLeaveRequests();
        assertEquals(leave1,leaveRequests.get(0));
        assertEquals(leave1,leaveRequests.get(1));
        assertEquals(leave1,leaveRequests.get(2));
    }

    @Test
    void shouldDeleteTheLeaveRequestWhenApproverVisitsThatLeaveRequest(){
        leaveManagement.applyLeave(1, "password1", organization, leave1);
        leaveManagement.validateLeaveRequest(4,"password4",organization);
        List<Leave> leaveRequests = approver.getLeaveRequests();
        assertTrue(leaveRequests.isEmpty());
    }

    @Test
    void shouldNotAddLeaveUntilApproverVisitsTheLeaveRequest(){
        leaveManagement.applyLeave(1,"password1",organization,leave1);
        List<Leave> leaves = employee1.getLeavesHistory();
        assertFalse(leaves.contains(leave1));
    }

    @Test
    void shouldAddLeaveHistoryInEmployeeObjectWhenApproverApprovesTheLeaveRequest(){
        leaveManagement.applyLeave(1,"password1",organization,leave1);
        leaveManagement.validateLeaveRequest(4,"password4",organization);
        List<Leave> leaveHistory = employee1.getLeavesHistory();
        assertTrue(leaveHistory.contains(leave1));
    }
}
