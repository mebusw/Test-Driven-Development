package training.budgetPeriod;

import java.util.Date;

public class BudgetCategory {
    public long getTotalAmountPeriod(Date startDate, Date endDate) {
        if (startDate.after(endDate))
            throw new RuntimeException("Start date cannot be before End Date!");

        return getTotalAmountPeriod(new Period(startDate, endDate));
    }

    private long getTotalAmountPeriod(Period period) {
        BudgetPeriod firstBudgetPeriod = createFirstBudgetPeriod(period);
        BudgetPeriod lastBudgetPeriod = createEndBudgetPeriod(period);

        double total = 0;
        for (BudgetPeriod budgetPeriod : firstBudgetPeriod.createBugdetPeriodListTill(lastBudgetPeriod)) {
            total += getAmountForOverlappingDays(period, budgetPeriod);
        }

        return (long) total;
    }

    private BudgetPeriod createFirstBudgetPeriod(Period period) {
        return new BudgetPeriod(getBudgetPeriodType(), period.getStartDate());
    }

    private BudgetPeriod createEndBudgetPeriod(Period period) {
        return new BudgetPeriod(getBudgetPeriodType(), period.getEndDate());
    }

    private double getAmountForOverlappingDays(Period period, BudgetPeriod firstBudgetPeriod) {
        long amountOfPeriod = getAmountFromBudgetPeriod(firstBudgetPeriod);
        long totalDaysInPeriod = firstBudgetPeriod.getAmountOfDays();
        long daysInPeriod = period.getAmountOfOverlappingDays(firstBudgetPeriod.getPeriod());
        return ((double) amountOfPeriod / (double) totalDaysInPeriod) * daysInPeriod;
    }

    private BudgetCategoryTypeMonthly getBudgetPeriodType() {
        return new BudgetCategoryTypeMonthly();
    }

    private long getAmountFromBudgetPeriod(BudgetPeriod firstBudgetPeriod) {
        return 9999;
    }

}
