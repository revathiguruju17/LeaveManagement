import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class LoginCommandTest {
    @Test
    void testWhetherItCallsTheLoginMethodInEmployeeWhenUserLogin(){
        Organization organizationMock = Mockito.mock(Organization.class);
        doNothing().when(organizationMock).employeeLogin("","");
        LoginCommand loginCommand = new LoginCommand();
        loginCommand.execute(organizationMock,null,null);
        verify(organizationMock,times(1)).employeeLogin(null,null);
    }
}
