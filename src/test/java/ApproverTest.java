import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ApproverTest {

    @Test
    void shouldReturnTrueIfTheConditionForLeaveIsSatisfied(){
        Approver approver = new Approver();
        assertTrue(approver.isAcceptLeave(8,5));
    }

    @Test
    void shouldReturnFalseIfTheConditionForLeaveIsNotSatisfied(){
        Approver approver = new Approver();
        assertFalse(approver.isAcceptLeave(3,4));
    }
}
