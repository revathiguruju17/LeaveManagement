import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmployeeAuthenticationTest {

    @Test
    void shouldReturnTrueIfTheEmployeeIsValidApprover(){
        Employee approver = new Approver(1,"password");
        assertTrue(EmployeeAuthentication.checkValidApprover(approver));
    }

    @Test
    void shouldReturnFalseIfTheEmployeeIsInValidApprover(){
        Employee approver = new Employee(1,"password");
        assertFalse(EmployeeAuthentication.checkValidApprover(approver));
    }
}
