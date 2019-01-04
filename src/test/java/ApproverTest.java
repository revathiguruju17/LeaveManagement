import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ApproverTest {

    @Test
    void shouldReturnLeavesApprovedMessageIfIfTheLeavesLeftAreGreaterThanAppliedLeaves(){
        Approver approver = new Approver();
        assertEquals("leave approved",approver.approveLeave(8,5));
    }

    @Test
    void shouldReturnNoLeavesLeftMessageIfTheAnnualLeavesAreOver(){
        Approver approver = new Approver();
        assertEquals("No Annual leaves left",approver.approveLeave(0,4));
    }

    @Test
    void shouldReturnMessageIfTheAnnualLeavesLeftAreLessThanAppliedLeaves(){
        Approver approver = new Approver();
        assertEquals("only 5 leaves are approved",approver.approveLeave(5,8));
    }
}
