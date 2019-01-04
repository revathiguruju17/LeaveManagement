import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ApproverTest {

    @Test
    void shouldReturnLeavesApprovedMessageIfIfTheLeavesLeftAreGreaterThanAppliedLeaves(){
        Approver approver = new Approver();
        assertEquals(LeaveState.APPROVED,approver.approveLeave(8,5));
    }

    @Test
    void shouldReturnNoLeavesLeftMessageIfTheAnnualLeavesAreOver(){
        Approver approver = new Approver();
        assertEquals(LeaveState.REJECTED,approver.approveLeave(0,4));
    }

    @Test
    void shouldReturnMessageIfTheAnnualLeavesLeftAreLessThanAppliedLeaves(){
        Approver approver = new Approver();
        assertEquals(LeaveState.PARTIALLY_APPROVED,approver.approveLeave(5,8));
    }
}
