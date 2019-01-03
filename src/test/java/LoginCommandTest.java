import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class LoginCommandTest {
    @Test
    void testWhetherItCallsTheLoginMethodInEmployeeWhenUserLogin(){
        Employee employee = Mockito.mock(Employee.class);
        doNothing().when(employee).login();
        LoginCommand loginCommand = new LoginCommand();
        loginCommand.execute(employee);
        verify(employee,times(1)).login();
    }
}
