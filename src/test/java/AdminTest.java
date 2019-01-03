import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AdminTest {

    @Test
    void shouldReturnTrueIfTheConditionForLeaveIsSatisfied(){
        Admin admin = new Admin();
        assertTrue(admin.isAcceptLeave(8,5));
    }

    @Test
    void shouldReturnFalseIfTheConditionForLeaveIsNotSatisfied(){
        Admin admin = new Admin();
        assertFalse(admin.isAcceptLeave(3,4));
    }
}
