import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {
    private Employee employee;
    private Approver approver;
    private Organization organization;
    private Date startDate;
    private Date endDate;

    @BeforeEach
    void init() {
        employee = new Employee(1, "password1", 3);
        organization = Mockito.mock(Organization.class);
        approver = Mockito.mock(Approver.class);
        organization.addApprover(approver);
        startDate = Mockito.mock(Date.class);
        endDate = Mockito.mock(Date.class);
    }

    @Test
    void shouldChangeTheStateOfEmployeeWhenLogin() {
        employee.login("password1");
        assertEquals(EmployeeState.LOGIN, employee.getState());
    }

    @Test
    void shouldChangeTheStateOfEmployeeWhenLogout() {
        employee.logout();
        assertEquals(EmployeeState.LOGOUT, employee.getState());
    }
}
