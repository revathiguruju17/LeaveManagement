import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ApproverTest {
    private Approver approver;

    @BeforeEach
    void init() {
        approver =  new Approver("id1","password1","approver1");
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
        approver.login("password1");
        assertEquals(EmployeeState.LOGIN, approver.getState());
    }

    @Test
    void shouldChangeTheApproverStateWhenLogout() {
        approver.logout();
        assertEquals(EmployeeState.LOGOUT, approver.getState());
    }
}
