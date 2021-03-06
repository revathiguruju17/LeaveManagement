import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {
    private Employee employee;

    @BeforeEach
    void init() {
        employee = new Employee(1, "password1");
    }

    @Test
    void shouldChangeTheStateOfEmployeeWhenLogin() {
        employee.login("password1");
        assertEquals(EmployeeState.LOGIN, employee.getState());
    }

    @Test
    void shouldThrowExceptionIfTheUserPasswordIsInvalid(){
        assertThrows(LoginInvalidException.class,()->employee.login("password"));
    }

    @Test
    void shouldChangeTheStateOfEmployeeWhenLogout() {
        employee.logout();
        assertEquals(EmployeeState.LOGOUT, employee.getState());
    }

    @Test
    void shouldReturnTrueIfTheIDIsValid(){
        assertTrue(employee.checkID(1));
    }

    @Test
    void shouldReturnFalseIfTheIDIsInValid(){
        assertFalse(employee.checkID(2));
    }

    @Test
    void shouldNotUpdateNumberOfLeavesLeftIfTheLeaveIsRejected(){
        int expected = employee.getNumberOfLeavesLeft();
        employee.updateNoOfLeavesLeft(LeaveState.REJECTED,12);
        assertEquals(expected,employee.getNumberOfLeavesLeft());
    }

    @Test
    void shouldUpdateNumberOfLeavesLeftIfTheLeaveIsApproved(){
        int expected = employee.getNumberOfLeavesLeft();
        expected = expected-6;
        employee.updateNoOfLeavesLeft(LeaveState.APPROVED,6);
        assertEquals(expected,employee.getNumberOfLeavesLeft());
    }

    @Test
    void shouldSetNumberOfLeavesLeftTo0IfTheLeaveIsPartiallyApproved(){
        employee.updateNoOfLeavesLeft(LeaveState.PARTIALLY_APPROVED,6);
        assertEquals(0,employee.getNumberOfLeavesLeft());
    }
}
