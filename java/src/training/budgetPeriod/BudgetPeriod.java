package training.budgetPeriod;

import java.util.Date;

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
}
