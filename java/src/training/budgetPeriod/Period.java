package training.budgetPeriod;

import java.util.Date;

public class Period {

    private Date startDate;
    private Date endDate;

    public Period(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Period() {
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public long getAmountOfDays() {
        return DateUtil.getDaysBetween(startDate, endDate, true);
    }

}
