import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DateValidatorTest {
    private SimpleDateFormat simpleDateFormat;

    @BeforeEach
    void init(){
        simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    }

    @Test
    void shouldReturnFalseIfTheLeaveStartDateAndEndDateIsPast() throws ParseException {
        Date startDate = simpleDateFormat.parse("21-12-2018");
        Date endDate = simpleDateFormat.parse("29-12-2018");
        assertFalse(DateValidator.checkWhetherTheLeaveAppliedIsForFutureOrNot(startDate, endDate));
    }

    @Test
    void shouldReturnFalseIfTheStartDateIsGreaterThanEndDate() throws ParseException{
        Date startDate = simpleDateFormat.parse("21-01-2019");
        Date endDate = simpleDateFormat.parse("13-01-2018");
        assertFalse(DateValidator.checkWhetherTheLeaveAppliedIsForFutureOrNot(startDate, endDate));
    }

    @Test
    void shouldReturnTrueIfTheStartDateAndEndDateIsNotPast() throws ParseException{
        Date startDate = simpleDateFormat.parse("12-02-2019");
        Date endDate = simpleDateFormat.parse("13-02-2019");
        assertTrue(DateValidator.checkWhetherTheLeaveAppliedIsForFutureOrNot(startDate, endDate));
    }

    @Test
    void shouldReturnTrueIfTheStartDateAndEndDateIsEqualAndGreaterThanCurrentDate() throws ParseException{
        Date startDate = simpleDateFormat.parse("12-01-2019");
        Date endDate = simpleDateFormat.parse("12-01-2019");
        assertTrue(DateValidator.checkWhetherTheLeaveAppliedIsForFutureOrNot(startDate, endDate));
    }

    @Test
    void shouldReturnFalseIfTheStartDateIsGreaterThanEndDateAndIsPast() throws ParseException{
        Date startDate = simpleDateFormat.parse("29-12-2018");
        Date endDate = simpleDateFormat.parse("27-12-2018");
        assertFalse(DateValidator.checkWhetherTheLeaveAppliedIsForFutureOrNot(startDate, endDate));
    }
}
