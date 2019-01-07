import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrganizationTest {
    private Organization organization;
    private Employee employee;

    @BeforeEach
    void init() {
        organization = new Organization();
        employee = new Employee("id1", "password1","approver1");
        organization.addEmployee(employee);
    }

    @Test
    void shouldReturnCorrectEmployeeWhenTheUserGivesID() {
        assertEquals(employee, organization.getEmployee("id1"));
    }

    @Test
    void shouldReturnNullWhenTheUserIsInvalid() {
        assertThrows(LoginInvalidException.class,()->organization.getEmployee("id2"));
    }
}
