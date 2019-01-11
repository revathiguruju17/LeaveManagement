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
    private Approver approver1;
    private Employee employee1;
    private Date startDate1;

    @BeforeEach
    void init() throws ParseException {
        simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        startDate1 = simpleDateFormat.parse("22-01-2019");
        Date endDate1 = simpleDateFormat.parse("24-01-2019");
        leave1 = new Leave(startDate1, endDate1);
        leaveManagement = new LeaveManagement();
        organization = new Organization();
        employee1 = new Employee(1, "password1");
        approver1 = new Approver(4, "password4");
        approver1.addEmployeeToTheApprover(1);
        organization.addEmployee(employee1);
        organization.addEmployee(approver1);
    }

    @Test
    void shouldThrowExceptionIfTheUserApplyLeaveWithWrongID() {
        assertThrows(LoginInvalidException.class, () -> leaveManagement.
                applyLeave(6, "password1", organization, leave1));
    }

    @Test
    void shouldAddLeaveToTheLeaveManagementIfTheValidEmployeeAppliesForALeave(){
        leaveManagement.applyLeave(1,"password1",organization,leave1);
        assertTrue(leaveManagement.checkLeaveRequests(leave1));
    }

    @Test
    void shouldThrowAnExceptionIfTheEmployeeAppliesALeaveWithInvalidDate() throws ParseException{
        Date startDate = simpleDateFormat.parse("20-01-2018");
        Date endDate = simpleDateFormat.parse("24-01-2018");
        Leave leave = new Leave(startDate, endDate);
        assertThrows(DateInvalidException.class,()->leaveManagement.applyLeave(1,"password1",organization,leave));
    }

    @Test
    void shouldNotValidateLeaveRequestIfTheApproverPasswordIsInvalid(){
        assertThrows(LoginInvalidException.class,()->leaveManagement.validateLeaveRequests(4,"password",organization));
    }

    @Test
    void shouldThrowIllegalAccessExceptionIfTheEmployeeTriesToValidateLeaveRequests(){
        assertThrows(IllegalAccessException.class,()->leaveManagement.validateLeaveRequests(1,"password1",organization));
    }

    @Test
    void shouldValidLeaveRequestsIfTheUserIsValidApprover() throws IllegalAccessException{
        leaveManagement.applyLeave(1,"password1",organization,leave1);
        leaveManagement.validateLeaveRequests(4,"password4",organization);
        assertTrue(leaveManagement.checkLeaveHistory(leave1));
        assertFalse(leaveManagement.checkLeaveRequests(leave1));
    }

    @Test
    void shouldNotAddLeaveToTheLeaveHistoryIfTheLeaveIsRejectedByTheApprover() throws ParseException, IllegalAccessException{
        Date startDate = simpleDateFormat.parse("10-02-2019");
        Date endDate = simpleDateFormat.parse("20-02-2019");
        Leave leave = new Leave(startDate, endDate);
        Date startDate1 = simpleDateFormat.parse("10-03-2019");
        Date endDate1 = simpleDateFormat.parse("13-03-2019");
        Leave leave1 = new Leave(startDate1, endDate1);
        leaveManagement.applyLeave(1,"password1",organization,leave);
        leaveManagement.applyLeave(1,"password1",organization,leave1);
        leaveManagement.validateLeaveRequests(4,"password4",organization);
        assertFalse(leaveManagement.checkLeaveHistory(leave1));
    }

    @Test
    void shouldThrowAnExceptionIfTheInvalidApproverTriesToSeeAllEmployeesHistory(){
        assertThrows(IllegalAccessException.class,()->leaveManagement.getLeaveHistoryOfEmployeeBasedOnTheDate(startDate1,1,"password1",organization));
    }

    @Test
    void shouldThrowAnExceptionIfTheApproverGivesInvalidPasswordToSeeLeaveHistory(){
        assertThrows(LoginInvalidException.class,()->leaveManagement.getLeaveHistoryOfEmployeeBasedOnTheDate(startDate1,4,"password1",organization));
    }

    @Test
    void shouldReturnTheLeaveHistoryBasedOnTheDateIfTheEmployeeIsAValidApprover() throws IllegalAccessException{
        leaveManagement.applyLeave(1,"password1",organization,leave1);
        leaveManagement.validateLeaveRequests(4,"password4",organization);
        List<Leave> leaveHistory = leaveManagement.getLeaveHistoryOfEmployeeBasedOnTheDate(startDate1,4,"password4",organization);
        assertTrue(leaveHistory.contains(leave1));
    }

    @Test
    void shouldReturnEmptyLeaveHistoryIfTheDateIsNotMatched() throws IllegalAccessException, ParseException{
        leaveManagement.applyLeave(1,"password1",organization,leave1);
        leaveManagement.validateLeaveRequests(4,"password4",organization);
        Date date = simpleDateFormat.parse("06-09-2019");
        List<Leave> leaveHistory = leaveManagement.getLeaveHistoryOfEmployeeBasedOnTheDate(date,4,"password4",organization);
        assertTrue(leaveHistory.isEmpty());
    }

    @Test
    void shouldThrowAnExceptionIfTheUserTriesToLoginWithWrongPasswordToSeeLeaveHistory(){
        assertThrows(LoginInvalidException.class,()->leaveManagement.getLeaveHistoryOfEmployee(1,"password",organization));
    }

    @Test
    void shouldReturnLeaveHistoryIfTheEmployeeEntersValidPassword() throws IllegalAccessException{
        leaveManagement.applyLeave(1,"password1",organization,leave1);
        leaveManagement.validateLeaveRequests(4,"password4",organization);
        List<Leave> leaveHistory = leaveManagement.getLeaveHistoryOfEmployee(1,"password1",organization);
        assertTrue(leaveHistory.contains(leave1));
    }
}
