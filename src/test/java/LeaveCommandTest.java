import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.doNothing;

class LeaveCommandTest {

    @Test
    void shouldCallApplyLeaveMethodInEmployeeWhenEmployeeAppliesForALeave(){
        Leave leave = Mockito.mock(Leave.class);
        Employee employee = Mockito.mock(Employee.class);
        LeaveCommand leaveCommand = new LeaveCommand();
        doNothing().when(employee).applyLeave(leave);
        leaveCommand.execute(employee,leave);
    }
}
