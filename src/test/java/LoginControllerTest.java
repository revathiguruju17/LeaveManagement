import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class LoginControllerTest {

    private IO IOMock;
    private LoginController loginController;
    private Employees employees;
    private Employee employee;

    @BeforeEach
    void init() {
        IOMock = Mockito.mock(ConsoleIO.class);
        loginController = new LoginController();
        employees = new Employees();
        employee = new Employee();
        employee.setID("revathi");
        employee.setPassword("revathi123");
        employees.addEmployee(employee);
    }

    @Test
    void shouldReturnSuccessfulMessageForSuccessfulLogin() {
        when(IOMock.readInput()).thenReturn("revathi", "revathi123");
        String message = loginController.login(IOMock, employees);
        assertEquals("login successful", message);
    }

    @Test
    void shouldReturnUnsuccessfulLoginWhenUserGivesWrongPassword() {
        when(IOMock.readInput()).thenReturn("revathi", "revathi4");
        String message = loginController.login(IOMock, employees);
        assertEquals("password is invalid", message);
    }

    @Test
    void shouldReturnInvalidUserMessageWhenUserGivesInvalidID() {
        when(IOMock.readInput()).thenReturn("puja", "revathi4");
        String message = loginController.login(IOMock, employees);
        assertEquals("userID is invalid", message);
    }

}
