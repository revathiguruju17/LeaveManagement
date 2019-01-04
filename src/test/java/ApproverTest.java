import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ApproverTest {
    private Approver approver;

    @BeforeEach
    void init() {
        approver =  new Approver("id","password");
    }

    @Test
    void shouldReturnLeavesApprovedMessageIfIfTheLeavesLeftAreGreaterThanAppliedLeaves() {
        assertEquals(LeaveState.APPROVED, approver.approveLeave(8, 5));
    }

    @Test
    void shouldReturnNoLeavesLeftMessageIfTheAnnualLeavesAreOver() {
        assertEquals(LeaveState.REJECTED, approver.approveLeave(0, 4));
    }

    @Test
    void shouldReturnMessageIfTheAnnualLeavesLeftAreLessThanAppliedLeaves() {
        assertEquals(LeaveState.PARTIALLY_APPROVED, approver.approveLeave(5, 8));
    }

    @Test
    void shouldChangeTheApproverStateWhenLogin() {
        approver.login();
        assertEquals(EmployeeState.LOGIN,approver.getEmployeeState());
    }

    @Test
    void shouldChangeTheApproverStateWhenLogout() {
        approver.logout();
        assertEquals(EmployeeState.LOGOUT,approver.getEmployeeState());
    }
}
