import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LeaveValidationTest {
    private LeaveValidation leaveValidation;

    @BeforeEach
    void init() {
        leaveValidation = new LeaveValidation();
    }

    @Test
    void shouldReturnFalseIfTheLeaveStartDateAndEndDateIsPast() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-mm-yyy");
        Date startDate = simpleDateFormat.parse("21-12-2018");
        Date endDate = simpleDateFormat.parse("29-12-2018");
        assertFalse(leaveValidation.checkWhetherTheLeaveAppliedIsForFutureOrNot(startDate, endDate));
    }

    @Test
    void shouldReturnFalseIfTheStartDateIsGreaterThanEndDate() throws ParseException{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-mm-yyy");
        Date startDate = simpleDateFormat.parse("21-01-2019");
        Date endDate = simpleDateFormat.parse("13-01-2018");
        assertFalse(leaveValidation.checkWhetherTheLeaveAppliedIsForFutureOrNot(startDate, endDate));
    }

    @Test
    void shouldReturnTrueIfTheStartDateAndEndDateIsNotPast() throws ParseException{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-mm-yyy");
        Date startDate = simpleDateFormat.parse("11-01-2019");
        Date endDate = simpleDateFormat.parse("12-01-2019");
        assertTrue(leaveValidation.checkWhetherTheLeaveAppliedIsForFutureOrNot(startDate, endDate));
    }

    @Test
    void shouldReturnTrueIfTheStartDateAndEndDateIsEqualAndGreaterThanCurrentDate() throws ParseException{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-mm-yyy");
        Date startDate = simpleDateFormat.parse("09-01-2019");
        Date endDate = simpleDateFormat.parse("09-01-2019");
        assertTrue(leaveValidation.checkWhetherTheLeaveAppliedIsForFutureOrNot(startDate, endDate));
    }

    @Test
    void shouldReturnFalseIfTheStartDateIsGreaterThanEndDateAndIsPast() throws ParseException{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-mm-yyy");
        Date startDate = simpleDateFormat.parse("29-12-2018");
        Date endDate = simpleDateFormat.parse("27-12-2018");
        assertFalse(leaveValidation.checkWhetherTheLeaveAppliedIsForFutureOrNot(startDate, endDate));
    }
}
