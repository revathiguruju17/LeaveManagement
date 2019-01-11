import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrganizationTest {
    private Organization organization;

    @BeforeEach
    void init() {
        organization = new Organization();
    }

    @Test
    void shouldReturnCorrectEmployeeWhenTheUserGivesValidID() {
        Employee employee1 = new Employee(1, "password1");
        organization.addEmployee(employee1);
        assertEquals(employee1, organization.getEmployee(1));
    }

    @Test
    void shouldThrowExceptionWhenTheEmployeeIDIsInvalid() {
        assertThrows(LoginInvalidException.class, () -> organization.getEmployee(4));
    }

    @Test
    void shouldReturnCorrectApproverWhenApproverGivesValidID() {
        Approver approver1 = new Approver(2, "password1");
        organization.addEmployee(approver1);
        assertEquals(approver1, organization.getEmployee(2));
    }

    @Test
    void shouldThrowExceptionWhenTheApproverIDIsInvalid() {
        assertThrows(LoginInvalidException.class, () -> organization.getEmployee(1));
    }


}
