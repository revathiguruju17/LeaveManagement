import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrganizationTest {
    private Organization organization;
    private Employee employee;

    @BeforeEach
    void init() {
        organization = new Organization();
        employee = new Employee("id1", "password1");
        organization.addEmployee(employee);
    }

    @Test
    void shouldReturnTrueWhenUserGivesValidIDAndPassword() {
        assertTrue(organization.employeeLogin("id1", "password1"));
    }

    @Test
    void shouldReturnFalseIfTheUserGivesInvalidIDAndPassword() {
        assertFalse(organization.employeeLogin("id2", "password2"));
    }

    @Test
    void shouldReturnCorrectEmployeeWhenTheUserGivesID() {
        assertEquals(employee, organization.getEmployee("id1"));
    }

    @Test
    void shouldReturnNullWhenTheUserIsInvalid() {
        assertNull(organization.getEmployee("id2"));
    }
}
