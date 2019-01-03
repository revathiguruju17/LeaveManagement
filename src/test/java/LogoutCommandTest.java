import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class LogoutCommandTest {
    @Test
    void testWhetherItCallsTheLoginMethodInEmployeeWhenUserLogin(){
        Employee employee = Mockito.mock(Employee.class);
        doNothing().when(employee).login();
        LogoutCommand logoutCommand = new LogoutCommand();
        logoutCommand.execute(employee);
        verify(employee,times(1)).logout();
    }
}
