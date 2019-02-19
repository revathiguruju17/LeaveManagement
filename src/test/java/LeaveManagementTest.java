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
    private Leave leave;
    private Date startDate;

    @BeforeEach
    void init() throws ParseException {
        simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        startDate = simpleDateFormat.parse("22-03-2029");
        Date endDate = simpleDateFormat.parse("24-03-2029");
        leave = new Leave(startDate, endDate);
        leaveManagement = new LeaveManagement();
        organization = new Organization();
        Employee employee = new Employee(1, "password1");
        Approver approver = new Approver(4, "password4");
        approver.addEmployeeToTheApprover(1);
        organization.addEmployee(employee);
        organization.addEmployee(approver);
    }

    @Test
    void shouldThrowExceptionIfTheUserApplyLeaveWithWrongID() {
        assertThrows(LoginInvalidException.class, () -> leaveManagement.
                applyLeave(6, "password1", organization, leave));
    }

    @Test
    void shouldAddLeaveToTheLeaveManagementIfTheValidEmployeeAppliesForALeave(){
        leaveManagement.applyLeave(1,"password1",organization, leave);
        assertTrue(leaveManagement.checkLeaveRequests(leave));
    }

    @Test
    void shouldThrowAnExceptionIfTheEmployeeAppliesALeaveWithInvalidDate() throws ParseException{
        Date startDate = simpleDateFormat.parse("20-03-2018");
        Date endDate = simpleDateFormat.parse("24-03-2018");
        Leave leave = new Leave(startDate, endDate);
        assertThrows(DateInvalidException.class,()->
                leaveManagement.applyLeave(1,"password1",organization,leave));
    }

    @Test
    void shouldNotValidateLeaveRequestIfTheApproverPasswordIsInvalid(){
        assertThrows(LoginInvalidException.class,()->
                leaveManagement.validateLeaveRequests(4,"password",organization));
    }

    @Test
    void shouldThrowIllegalAccessExceptionIfTheEmployeeTriesToValidateLeaveRequests(){
        assertThrows(IllegalAccessException.class,()->
                leaveManagement.validateLeaveRequests(1,"password1",organization));
    }

    @Test
    void shouldValidLeaveRequestsIfTheUserIsValidApprover() throws IllegalAccessException{
        leaveManagement.applyLeave(1,"password1",organization, leave);
        leaveManagement.validateLeaveRequests(4,"password4",organization);
        assertTrue(leaveManagement.checkLeaveHistory(leave));
        assertFalse(leaveManagement.checkLeaveRequests(leave));
    }

    @Test
    void shouldNotAddLeaveToTheLeaveHistoryIfTheLeaveIsRejectedByTheApprover() throws ParseException, IllegalAccessException{
        Date startDate = simpleDateFormat.parse("10-03-2029");
        Date endDate = simpleDateFormat.parse("20-03-2029");
        Leave leave = new Leave(startDate, endDate);
        Date startDate1 = simpleDateFormat.parse("10-03-2029");
        Date endDate1 = simpleDateFormat.parse("13-03-2029");
        Leave leave1 = new Leave(startDate1, endDate1);
        leaveManagement.applyLeave(1,"password1",organization,leave);
        leaveManagement.applyLeave(1,"password1",organization,leave1);
        leaveManagement.validateLeaveRequests(4,"password4",organization);
        assertFalse(leaveManagement.checkLeaveHistory(leave1));
    }

    @Test
    void shouldThrowAnExceptionIfTheInvalidApproverTriesToSeeAllEmployeesHistory(){
        assertThrows(IllegalAccessException.class,()->leaveManagement.
                getLeaveHistoryOfEmployeeBasedOnTheDate(startDate,1,"password1",organization));
    }

    @Test
    void shouldThrowAnExceptionIfTheApproverGivesInvalidPasswordToSeeLeaveHistory(){
        assertThrows(LoginInvalidException.class,()->leaveManagement.
                getLeaveHistoryOfEmployeeBasedOnTheDate(startDate,4,"password1",organization));
    }

    @Test
    void shouldReturnTheLeaveHistoryBasedOnTheDateIfTheEmployeeIsAValidApprover() throws IllegalAccessException{
        leaveManagement.applyLeave(1,"password1",organization, leave);
        leaveManagement.validateLeaveRequests(4,"password4",organization);
        List<Leave> leaveHistory = leaveManagement.
                getLeaveHistoryOfEmployeeBasedOnTheDate(startDate,4,"password4",organization);
        assertTrue(leaveHistory.contains(leave));
    }

    @Test
    void shouldReturnEmptyLeaveHistoryIfTheDateIsNotMatched() throws IllegalAccessException, ParseException{
        leaveManagement.applyLeave(1,"password1",organization, leave);
        leaveManagement.validateLeaveRequests(4,"password4",organization);
        Date date = simpleDateFormat.parse("06-09-2019");
        List<Leave> leaveHistory = leaveManagement.
                getLeaveHistoryOfEmployeeBasedOnTheDate(date,4,"password4",organization);
        assertTrue(leaveHistory.isEmpty());
    }

    @Test
    void shouldThrowAnExceptionIfTheUserTriesToLoginWithWrongPasswordToSeeLeaveHistory(){
        assertThrows(LoginInvalidException.class,()->leaveManagement.
                getLeaveHistoryOfEmployee(1,"password",organization));
    }

    @Test
    void shouldReturnLeaveHistoryIfTheEmployeeEntersValidPassword() throws IllegalAccessException{
        leaveManagement.applyLeave(1,"password1",organization, leave);
        leaveManagement.validateLeaveRequests(4,"password4",organization);
        List<Leave> leaveHistory = leaveManagement.getLeaveHistoryOfEmployee(1,"password1",organization);
        assertTrue(leaveHistory.contains(leave));
    }
}
