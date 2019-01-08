import java.util.Date;

class DateValidator {
    static boolean checkWhetherTheLeaveAppliedIsForFutureOrNot(Date startDate, Date endDate) {
        Date currentDate = new Date();
        return startDate.compareTo(currentDate) > 0 && endDate.compareTo(startDate) >= 0;
    }
}
