package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.ConsoleIO;
import view.IO;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {
    private Employee employee;

    @BeforeEach
    void init() {
        employee = new Employee("revathi","123456");
    }

    @Test
    void shouldReturnTrueForValidUserID() {
        assertTrue(employee.compareID("revathi"));
    }

    @Test
    void shouldReturnFalseForInValidUserID() {
        assertFalse(employee.compareID("lavanya"));
    }

    @Test
    void shouldReturnTrueForValidPassword() {
        assertTrue(employee.comparePassword("123456"));
    }

    @Test
    void shouldReturnFalseForInValidPassword() {
        assertFalse(employee.comparePassword("3333333"));
    }

    @Test
    void shouldUpdateNoOfLeavesLeftIfAnEmployeeApplyForALeave(){
        employee.updateNoOfLeaves(2);
        assertEquals(22,employee.getNumberOfAnnualLeavesLeft());
    }

    @Test
    void shouldUpdateTheLeavesIfAnEmployeeApplyForALeave(){
        IO io = new ConsoleIO();
        Date startDate = new Date(2018,12,1);
        Date endDate = new Date(2018,12,1);
        Leave leave = new Leave(1,startDate,endDate,LeaveType.ANNUAL);
        Admin admin = new Admin();
        employee.applyLeave(leave,admin,io);
    }
}
