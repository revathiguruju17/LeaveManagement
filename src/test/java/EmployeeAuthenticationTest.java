import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmployeeAuthenticationTest {
    private EmployeeAuthentication employeeAuthentication;

    @BeforeEach
    void init(){
        employeeAuthentication = new EmployeeAuthentication();
    }

    @Test
    void shouldReturnTrueIfTheEmployeeIsValidApprover(){
        Employee approver = new Approver(1,"password");
        assertTrue(employeeAuthentication.checkValidApprover(approver));
    }

    @Test
    void shouldReturnFalseIfTheEmployeeIsInValidApprover(){
        Employee approver = new Employee(1,"password");
        assertFalse(employeeAuthentication.checkValidApprover(approver));
    }
}
