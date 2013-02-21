package training.budgetPeriod;

import java.util.Date;

public class Period {

    private Date start;
    private Date end;

    public Period(Date start, Date end) {
        this.start = start;
        this.end = end;
    }

    public Date getStartDate() {
        return start;
    }

    public Date getEndDate() {
        return end;
    }

    public long getAmountOfDays() {
        return DateUtil.getDaysBetween(start, end, true);
    }

    public long getAmountOfOverlappingDays(Period period) {
        Date largestStartDate = (start.after(period.start)) ? start : period.start;
        Date smallestEndDate = (end.before(period.end)) ? end : period.end;
        if (smallestEndDate.before(largestStartDate))
            return 0;
        return new Period(largestStartDate, smallestEndDate).getAmountOfDays();
    }

}
