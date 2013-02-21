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
            double amountInPeriod = getAmountInPeriod(startDate, endDate);
            return (long) amountInPeriod;
        }
        // If the area between Start and End overlap at least two budget
        // periods.
        if (getBudgetPeriodType().getBudgetPeriodOffset(startDate, 1).equals(
                getBudgetPeriodType().getStartOfBudgetPeriod(endDate))
                || getBudgetPeriodType().getBudgetPeriodOffset(startDate, 1)
                        .before(getBudgetPeriodType().getStartOfBudgetPeriod(
                                endDate))) {
            Date endOfBudgetPeriod = getBudgetPeriodType()
                    .getEndOfBudgetPeriod(startDate);
            double totalStartPeriod = getAmountInPeriod(startDate,
                    endOfBudgetPeriod);

            double totalInMiddle = 0;
            for (String periodKey : getBudgetPeriods(getBudgetPeriodType()
                    .getBudgetPeriodOffset(startDate, 1), getBudgetPeriodType()
                    .getBudgetPeriodOffset(endDate, -1))) {
                totalInMiddle += getAmount(getPeriodDate(periodKey));
            }

            Date startOfBudgetPeriod = getBudgetPeriodType()
                    .getStartOfBudgetPeriod(endDate);
            double totalEndPeriod = getAmountInPeriod(startOfBudgetPeriod,
                    endDate);

            return (long) (totalStartPeriod + totalInMiddle + totalEndPeriod);
        }
        throw new RuntimeException(
                "You should not be here. We have returned all legitimate numbers from getAmount(Date, Date) in BudgetCategoryImpl.Please contact Wyatt Olson with details on how you got here (what steps did you perform in Buddi to get this error message).");
    }

    private double getAmountInPeriod(Date startDate, Date endDate) {
        long amountOfPeriod = getAmount(startDate);
        long totalDaysInPeriod = getBudgetPeriodType().getDaysInPeriod(
                startDate);
        long daysInPeriod = DateUtil.getDaysBetween(
                startDate, endDate, true);
        double totalInPeriod = ((double) amountOfPeriod / (double) totalDaysInPeriod)
                * daysInPeriod;
        return totalInPeriod;
    }

    ////////// Below are stubs
    private Date getPeriodDate(String periodKey) {
        // TODO Auto-generated method stub
        return new Date();
    }

    private List<String> getBudgetPeriods(Date budgetPeriodOffset,
            Date budgetPeriodOffset2) {
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
