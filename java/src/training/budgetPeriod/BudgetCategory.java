package training.budgetPeriod;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BudgetCategory {
    public long getTotalAmountPeriod(Date startDate, Date endDate) {
        if (startDate.after(endDate))
            throw new RuntimeException("Start date cannot be before End Date!");

        return getTotalAmountPeriod(new Period(startDate, endDate));
    }

    private long getTotalAmountPeriod(Period period) {
        BudgetPeriod firstBudgetPeriod = createBudgetPeriodFromDate(period.getStartDate());
        BudgetPeriod lastBudgetPeriod = createBudgetPeriodFromDate(period.getEndDate());

        if (firstBudgetPeriod.equals(lastBudgetPeriod)) {
            return (long) getAmountForPeriodWithinBudgetPeriod(period, firstBudgetPeriod);
        }

        double totalStartPeriod = getAmountForPeriodWithinBudgetPeriod(new Period(period.getStartDate(),
                firstBudgetPeriod.getEndDate()), firstBudgetPeriod);

        double totalInMiddle = 0;
        for (String periodKey : getBudgetPeriods(getBudgetPeriodType()
                .getStartOfNextBudgetPeriod(period.getStartDate()), getBudgetPeriodType()
                .getStartOfPreviousBudgetPeriod(period.getEndDate()))) {
            totalInMiddle += getAmountFromBudgetPeriodContainingDate(getPeriodDate(periodKey));
        }

        double totalEndPeriod = getAmountForPeriodWithinBudgetPeriod(
                new Period(lastBudgetPeriod.getStartDate(), period.getEndDate()), lastBudgetPeriod);

        return (long) (totalStartPeriod + totalInMiddle + totalEndPeriod);
    }

    private BudgetPeriod createBudgetPeriodFromDate(Date date) {
        return new BudgetPeriod(getBudgetPeriodType(), date);
    }

    private double getAmountForPeriodWithinBudgetPeriod(Period period, BudgetPeriod firstBudgetPeriod) {
        long amountOfPeriod = getAmountFromBudgetPeriodContainingDate(period.getStartDate());
        long totalDaysInPeriod = getBudgetPeriodType().getDaysInPeriod(period.getStartDate());
        long daysInPeriod = DateUtil.getDaysBetween(period.getStartDate(), period.getEndDate(), true);
        double totalInPeriod = ((double) amountOfPeriod / (double) totalDaysInPeriod) * daysInPeriod;
        return totalInPeriod;
    }

    private BudgetCategoryTypeMonthly getBudgetPeriodType() {
        return new BudgetCategoryTypeMonthly();
    }

    // //////// Below are stubs
    private Date getPeriodDate(String periodKey) {
        // TODO Auto-generated method stub
        return new Date();
    }

    private List<String> getBudgetPeriods(Date budgetPeriodOffset, Date budgetPeriodOffset2) {
        // TODO Auto-generated method stub
        return new ArrayList<String>();
    }

    private long getAmountFromBudgetPeriodContainingDate(Date date) {
        // TODO Auto-generated method stub
        return 9999;
    }

}
