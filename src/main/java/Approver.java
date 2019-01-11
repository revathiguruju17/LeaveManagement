import java.util.ArrayList;
import java.util.List;

class Approver extends Employee {

    private List<Integer> employees;

    Approver(int ID, String password) {
        super(ID, password);
        employees = new ArrayList<>();
    }

    @Override
    public LeaveState approveLeave(int numberOfLeavesLeft, int numberOfLeavesWant) {
        if (numberOfLeavesLeft == 0) {
            return LeaveState.REJECTED;
        } else if (numberOfLeavesLeft >= numberOfLeavesWant)
            return LeaveState.APPROVED;
        else {
            return LeaveState.PARTIALLY_APPROVED;
        }
    }

    @Override
    List<Integer> getEmployeesOfTheApprover() {
        return employees;
    }

    void addEmployeeToTheApprover(int employeeID){
        employees.add(employeeID);
    }
}
