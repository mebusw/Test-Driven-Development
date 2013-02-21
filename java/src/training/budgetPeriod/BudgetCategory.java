package training.budgetPeriod;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
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

    private double getAmountFromBudgetPeriodContainingDate(Period periodDate) {
        // TODO Auto-generated method stub
        return 0;
    }

    private BudgetPeriod createBudgetPeriodFromDate(Date date) {
        return new BudgetPeriod(getBudgetPeriodType(), date);
    }

    private double getAmountForPeriodWithinBudgetPeriod(Period period, BudgetPeriod firstBudgetPeriod) {
        long amountOfPeriod = getAmountFromBudgetPeriod(firstBudgetPeriod);
        long totalDaysInPeriod = firstBudgetPeriod.getAmountOfDays();
        long daysInPeriod = period.getAmountOfDays();
        return ((double) amountOfPeriod / (double) totalDaysInPeriod) * daysInPeriod;
    }

    private BudgetCategoryTypeMonthly getBudgetPeriodType() {
        return new BudgetCategoryTypeMonthly();
    }

    // //////// Below are stubs
    private Period getPeriodDate(String periodKey) {
        return new Period();
    }

    private List<String> getBudgetPeriods(Date startDate, Date endDate) {
        List<String> budgetPeriodKeys = new LinkedList<String>();
        Date temp = getBudgetPeriodType().getStartOfBudgetPeriod(startDate);
        while (temp.before(getBudgetPeriodType().getEndOfBudgetPeriod(endDate))) {
            budgetPeriodKeys.add(getPeriodKey(temp));
            temp = getBudgetPeriodType().getBudgetPeriodOffset(temp, 1);
        }
        return budgetPeriodKeys;
    }

    private String getPeriodKey(Date temp) {
        // TODO Auto-generated method stub
        return null;
    }

    private long getAmountFromBudgetPeriod(BudgetPeriod firstBudgetPeriod) {
        return 9999;
    }

}
