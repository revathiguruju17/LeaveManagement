import java.util.Date;

public class LeaveValidation {
    boolean checkWhetherTheLeaveAppliedIsForFutureOrNot(Date startDate, Date endDate) {
        Date currentDate = new Date();
        return startDate.compareTo(currentDate) > 0 && endDate.compareTo(startDate) >= 0;
    }
}
