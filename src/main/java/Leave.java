import java.util.Date;

class Leave {
    private int employeeID;
    private Date startDate;
    private Date endDate;
    private int NumberOfLeaves;

    Leave(Date startDate, Date endDate) {
        this.NumberOfLeaves = durationBetweenTwoDates(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    Date getStartDate() {
        return startDate;
    }

    Date getEndDate() {
        return endDate;
    }

    int getNumberOfLeaves() {
        return NumberOfLeaves;
    }

    void setEmployeeID(int ID) {
        this.employeeID = ID;
    }

    private int durationBetweenTwoDates(Date one, Date two) {
        long difference = (one.getTime() - two.getTime()) / 86400000;
        return (int) Math.abs(difference);
    }

}
