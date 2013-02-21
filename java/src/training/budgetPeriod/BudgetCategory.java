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
            return (long) getAmgetAmountForPeriodWithinBudgetPeriodOfStartDateountInPeriod(period.getStartDate(),
                    period.getEndDate());
        }

        double totalStartPeriod = getAmgetAmountForPeriodWithinBudgetPeriodOfStartDateountInPeriod(
                period.getStartDate(), firstBudgetPeriod.getEndDate());

        double totalInMiddle = 0;
        for (String periodKey : getBudgetPeriods(getBudgetPeriodType()
                .getStartOfNextBudgetPeriod(period.getStartDate()), getBudgetPeriodType()
                .getStartOfPreviousBudgetPeriod(period.getEndDate()))) {
            totalInMiddle += getAmountFromBudgetPeriodContainingDate(getPeriodDate(periodKey));
        }

        double totalEndPeriod = getAmgetAmountForPeriodWithinBudgetPeriodOfStartDateountInPeriod(
                lastBudgetPeriod.getStartDate(), period.getEndDate());

        return (long) (totalStartPeriod + totalInMiddle + totalEndPeriod);
    }

    private BudgetPeriod createBudgetPeriodFromDate(Date date) {
        return new BudgetPeriod(getBudgetPeriodType(), date);
    }

    private double getAmgetAmountForPeriodWithinBudgetPeriodOfStartDateountInPeriod(Date startDate, Date endDate) {
        long amountOfPeriod = getAmountFromBudgetPeriodContainingDate(startDate);
        long totalDaysInPeriod = getBudgetPeriodType().getDaysInPeriod(startDate);
        long daysInPeriod = DateUtil.getDaysBetween(startDate, endDate, true);
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
