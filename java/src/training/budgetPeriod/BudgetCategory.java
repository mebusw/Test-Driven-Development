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
        // If Start and End are in the same budget period
        if (getBudgetPeriodType().getStartOfBudgetPeriod(period.getStartDate()).equals(
                getBudgetPeriodType().getStartOfBudgetPeriod(period.getEndDate()))) {
            return (long) getAmountInPeriod(period.getStartDate(), period.getEndDate());
        }

        // If the area between Start and End overlap at least two budget
        // periods.
        if (getBudgetPeriodType().getStartOfNextBudgetPeriod(period.getStartDate()).equals(
                getBudgetPeriodType().getStartOfBudgetPeriod(period.getEndDate()))
                || getBudgetPeriodType().getStartOfNextBudgetPeriod(period.getStartDate()).before(
                        getBudgetPeriodType().getStartOfBudgetPeriod(period.getEndDate()))) {
            double totalStartPeriod = getAmountInPeriod(period.getStartDate(), getBudgetPeriodType()
                    .getEndOfBudgetPeriod(period.getStartDate()));

            double totalInMiddle = 0;
            for (String periodKey : getBudgetPeriods(
                    getBudgetPeriodType().getStartOfNextBudgetPeriod(period.getStartDate()), getBudgetPeriodType()
                            .getStartOfPreviousBudgetPeriod(period.getEndDate()))) {
                totalInMiddle += getAmountFromBudgetPeriodContainingDate(getPeriodDate(periodKey));
            }

            double totalEndPeriod = getAmountInPeriod(
                    getBudgetPeriodType().getStartOfBudgetPeriod(period.getEndDate()), period.getEndDate());

            return (long) (totalStartPeriod + totalInMiddle + totalEndPeriod);
        }
        throw new RuntimeException(
                "You should not be here. We have returned all legitimate numbers from getAmount(Date, Date) in BudgetCategoryImpl.Please contact Wyatt Olson with details on how you got here (what steps did you perform in Buddi to get this error message).");
    }

    private double getAmountInPeriod(Date startDate, Date endDate) {
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
