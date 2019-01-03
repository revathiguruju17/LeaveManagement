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
    void shouldUpdateEmployeeLoginStateWhenUserEntersValidIDAndPassword() {
        Employee employee = new Employee("id1", "password1");
        organization.addEmployee(employee);
        EmployeeState stateBeforeLogin = employee.getState();
        organization.employeeLogin("id1", "password1");
        EmployeeState stateAfterLogin = employee.getState();
        assertNotEquals(stateAfterLogin, stateBeforeLogin);
    }

    @Test
    void shouldThrowAnExceptionIfTheUserGivesInvalidIDAndPassword() {
        Employee employee = new Employee("id1", "password1");
        organization.addEmployee(employee);
        assertThrows(LoginInvalidException.class, () -> organization.employeeLogin("id", "password"));
    }
}
