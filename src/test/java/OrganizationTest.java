import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class OrganizationTest {
    private Organization organization;

    @BeforeEach
    void init(){
        organization = new Organization();
    }

    @Test
    void shouldReturnCorrectEmployeeWhenTheUserGivesValidID() {
        Employee employee1 = new Employee(1,"password1", 2);
        organization.addEmployee(employee1);
        assertEquals(employee1, organization.getEmployee(1));
    }

    @Test
    void shouldThrowExceptionWhenTheEmployeeIDIsInvalid() {
        assertThrows(LoginInvalidException.class, () -> organization.getEmployee(4));
    }

    @Test
    void shouldReturnCorrectApproverWhenApproverGivesValidID(){
        Approver approver1 = new Approver(2,"password1", 3);
        organization.addEmployee(approver1);
        assertEquals(approver1,organization.getEmployee(2));
    }

    @Test
    void shouldThrowExceptionWhenTheApproverIDIsInvalid() {
        assertThrows(LoginInvalidException.class, () -> organization.getEmployee(1));
    }


    @Test
    void shouldReturnEmptyLeaveHistoryOfEmployeeIfTheDateIsNotMatched() throws ParseException{
        Employee employee = Mockito.mock(Employee.class);
        organization.addEmployee(employee);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date startDate = simpleDateFormat.parse("21-01-2018");
        Date endDate = simpleDateFormat.parse("23-01-2018");
        Leave leave = new Leave(startDate,endDate);
        List<Leave> leaves = new ArrayList<>();
        leaves.add(leave);
        when(employee.getLeavesHistory()).thenReturn(leaves);
        List<Leave> result =organization.getEmployeesLeaveHistoryBasedOnDate(simpleDateFormat.parse("25-01-2018"));
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnEmployeeLeaveHistoryBasedOnDate() throws ParseException {
        Employee employee1 = Mockito.mock(Employee.class);
        organization.addEmployee(employee1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date startDate = simpleDateFormat.parse("21-01-2019");
        Date endDate = simpleDateFormat.parse("23-01-2019");
        Leave leave = new Leave(startDate,endDate);
        List<Leave> expected = new ArrayList<>();
        expected.add(leave);
        when(employee1.getLeavesHistory()).thenReturn(expected);
        List<Leave> result =organization.getEmployeesLeaveHistoryBasedOnDate(startDate);
        assertTrue(result.contains(leave));
        assertEquals(1,result.size());
    }

    @Test
    void shouldReturnEmptyLeaveHistoryOfApproverIfTheDateIsNotMatched() throws ParseException{
        Approver approver = Mockito.mock(Approver.class);
        organization.addApprover(approver);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date startDate = simpleDateFormat.parse("21-01-2018");
        Date endDate = simpleDateFormat.parse("23-01-2018");
        Leave leave = new Leave(startDate,endDate);
        List<Leave> leaves = new ArrayList<>();
        leaves.add(leave);
        when(approver.getLeavesHistory()).thenReturn(leaves);
        List<Leave> result =organization.getEmployeesLeaveHistoryBasedOnDate(simpleDateFormat.parse("25-01-2018"));
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnApproverLeaveHistoryBasedOnDate() throws ParseException {
        Approver approver = Mockito.mock(Approver.class);
        organization.addApprover(approver);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date startDate = simpleDateFormat.parse("21-01-2019");
        Date endDate = simpleDateFormat.parse("23-01-2019");
        Leave leave = new Leave(startDate,endDate);
        List<Leave> leaves = new ArrayList<>();
        leaves.add(leave);
        when(approver.getLeavesHistory()).thenReturn(leaves);
        assertEquals(leaves,organization.getApproversLeaveHistoryBasedOnDate(startDate));
    }
}
