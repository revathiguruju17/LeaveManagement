import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class OrganizationTest {
    private Organization organization;
    private Employee employeeMock;

    @BeforeEach
    void init(){
       organization = new Organization();
       employeeMock = Mockito.mock(Employee.class);
       organization.addEmployee(employeeMock);
    }

    @Test
    void checkingWhetherTheCompareIDMethodIsCalledOrNot(){
        when(employeeMock.compareID("revathi")).thenReturn(true);
        assertTrue(organization.checkID("revathi"));
    }


    @Test
    void checkingWhetherTheComparePasswordMethodIsCalledOrNot(){
        when(employeeMock.comparePassword("123456")).thenReturn(true);
        assertTrue(organization.checkPassword("123456"));
    }

    @Test
    void shouldAddTheNewEmployeeToTheListCorrectly(){
        assertTrue(organization.contains(employeeMock));
    }
}
