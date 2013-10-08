package training.budgetPeriod;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class BudgetPeriod {
    private Period period;
    private BudgetCategoryTypeMonthly type;

    public BudgetPeriod(BudgetCategoryTypeMonthly type, Date date) {
        period = new Period(type.getStartOfBudgetPeriod(date), type.getEndOfBudgetPeriod(date));
        this.type = type;
    }

    @Override
    public boolean equals(Object object) {
        BudgetPeriod otherBudgetPeriod = (BudgetPeriod) object;
        return otherBudgetPeriod.period.equals(this.period);
    }

    public BudgetPeriod nextBudgetPeriod() {
        return new BudgetPeriod(type, type.getBudgetPeriodOffset(period.getStartDate(), 1));
    }

    public Date getStartDate() {
        return period.getStartDate();
    }

    public Date getEndDate() {
        return period.getEndDate();
    }

    public BudgetPeriod previousBudgetPeriod() {
        return new BudgetPeriod(type, type.getBudgetPeriodOffset(period.getStartDate(), -1));
    }

    public long getAmountOfDays() {
        return type.getDaysInPeriod(period.getStartDate());
    }

    public List<BudgetPeriod> createBugdetPeriodListTill(BudgetPeriod lastBudgetPeriod) {
        List<BudgetPeriod> budgetPeriodKeys = new LinkedList<BudgetPeriod>();
        BudgetPeriod currentBudgetPeriod = this;

        while (currentBudgetPeriod.getStartDate().before(lastBudgetPeriod.getEndDate())) {
            budgetPeriodKeys.add(currentBudgetPeriod);
            currentBudgetPeriod = currentBudgetPeriod.nextBudgetPeriod();
        }
        return budgetPeriodKeys;
    }

    public Period getPeriod() {
        return this.period;
    }

}
