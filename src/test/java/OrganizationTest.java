import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrganizationTest {

    @Test
    void shouldReturnCorrectEmployeeWhenTheUserGivesValidID() {
        Organization organization = new Organization();
        Employee employee1 = new Employee(1,"password1", 2);
        organization.addEmployee(employee1);
        assertEquals(employee1, organization.getEmployee(1));
    }

    @Test
    void shouldThrowExceptionWhenTheEmployeeIDIsInvalid() {
        Organization organization = new Organization();
        assertThrows(LoginInvalidException.class, () -> organization.getEmployee(4));
    }

    @Test
    void shouldReturnCorrectApproverWhenApproverGivesValidID(){
        Organization organization = new Organization();
        Approver approver1 = new Approver(2,"password1", 3);
        organization.addEmployee(approver1);
        assertEquals(approver1,organization.getEmployee(2));
    }

    @Test
    void shouldThrowExceptionWhenTheApproverIDIsInvalid() {
        Organization organization = new Organization();
        assertThrows(LoginInvalidException.class, () -> organization.getEmployee(1));
    }
}
