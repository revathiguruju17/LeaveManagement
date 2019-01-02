package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import view.ConsoleIO;
import view.IO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class LoginTest {

    private IO IOMock;
    private Login login;
    private Employees employees;
    private Employee employee;

    @BeforeEach
    void init() {
        IOMock = Mockito.mock(ConsoleIO.class);
        login = new Login();
        employees = new Employees();
        employee = new Employee("revathi","revathi123");
        employees.addEmployee(employee);
    }

    @Test
    void shouldReturnSuccessfulMessageForSuccessfulLogin() {
        when(IOMock.readInput()).thenReturn("revathi", "revathi123");
        String message = login.employeeLogin(IOMock, employees);
        assertEquals("employeeLogin successful", message);
    }

    @Test
    void shouldReturnUnsuccessfulLoginWhenUserGivesWrongPassword() {
        when(IOMock.readInput()).thenReturn("revathi", "revathi4");
        String message = login.employeeLogin(IOMock, employees);
        assertEquals("password is invalid", message);
    }

    @Test
    void shouldReturnInvalidUserMessageWhenUserGivesInvalidID() {
        when(IOMock.readInput()).thenReturn("puja", "revathi4");
        String message = login.employeeLogin(IOMock, employees);
        assertEquals("userID is invalid", message);
    }

}
