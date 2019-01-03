import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

class DisplayLeavesCommandTest {

    @Test
    void shouldCallDisplayMethodInEmployeeWhenUserWantsListOfLeaves() {
        Employee employee = Mockito.mock(Employee.class);
        when(employee.getNumberOfLeavesTaken()).thenReturn(null);
        DisplayLeavesCommand displayLeavesCommand = new DisplayLeavesCommand();
        assertNull(displayLeavesCommand.execute(employee));
    }
}
