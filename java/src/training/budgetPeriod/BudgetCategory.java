package training.budgetPeriod;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BudgetCategory {
    public long getAmount(Date startDate, Date endDate) {
        if (startDate.after(endDate))
            throw new RuntimeException("Start date cannot be before End Date!");
        // If Start and End are in the same budget period
        if (getBudgetPeriodType().getStartOfBudgetPeriod(startDate).equals(
                getBudgetPeriodType().getStartOfBudgetPeriod(endDate))) {
            return (long) getAmountInPeriod(startDate, endDate);
        }
        // If the area between Start and End overlap at least two budget
        // periods.
        if (getBudgetPeriodType().getStartOfNextBudgetPeriod(startDate).equals(
                getBudgetPeriodType().getStartOfBudgetPeriod(endDate))
                || getBudgetPeriodType().getStartOfNextBudgetPeriod(startDate).before(
                        getBudgetPeriodType().getStartOfBudgetPeriod(endDate))) {
            double totalStartPeriod = getAmountInPeriod(startDate, getBudgetPeriodType().getEndOfBudgetPeriod(startDate));

            double totalInMiddle = 0;
            for (String periodKey : getBudgetPeriods(getBudgetPeriodType().getStartOfNextBudgetPeriod(startDate),
                    getBudgetPeriodType().getStartOfPreviousBudgetPeriod(endDate))) {
                totalInMiddle += getAmount(getPeriodDate(periodKey));
            }

            double totalEndPeriod = getAmountInPeriod(getBudgetPeriodType().getStartOfBudgetPeriod(endDate), endDate);

            return (long) (totalStartPeriod + totalInMiddle + totalEndPeriod);
        }
        throw new RuntimeException(
                "You should not be here. We have returned all legitimate numbers from getAmount(Date, Date) in BudgetCategoryImpl.Please contact Wyatt Olson with details on how you got here (what steps did you perform in Buddi to get this error message).");
    }

    private double getAmountInPeriod(Date startDate, Date endDate) {
        long amountOfPeriod = getAmount(startDate);
        long totalDaysInPeriod = getBudgetPeriodType().getDaysInPeriod(startDate);
        long daysInPeriod = DateUtil.getDaysBetween(startDate, endDate, true);
        double totalInPeriod = ((double) amountOfPeriod / (double) totalDaysInPeriod) * daysInPeriod;
        return totalInPeriod;
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

    private long getAmount(Date startDate) {
        // TODO Auto-generated method stub
        return 9999;
    }

    private BudgetCategoryTypeMonthly getBudgetPeriodType() {
        return new BudgetCategoryTypeMonthly();
    }
}
