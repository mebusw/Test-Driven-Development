package training.budgetPeriod;

import java.util.Date;

public class BudgetCategoryTypeMonthly {
    public Date getStartOfBudgetPeriod(Date date) {
        return DateUtil.getStartOfMonth(date);
        
    }

    public Date getEndOfBudgetPeriod(Date date) {
        return DateUtil.getEndOfMonth(date);
    }

    public Date getBudgetPeriodOffset(Date date, int offset) {
        return getStartOfBudgetPeriod(DateUtil.addMonths(
                DateUtil.getStartOfMonth(date), 1 * offset));
    }

    public long getDaysInPeriod(Date date) {
        return DateUtil.getDaysInMonth(date);
    }

    public String getDateFormat() {
        return "MMM yyyy";
    }

    public String getName() {
        return "BudgetCategoryTypes.BUDGET_CATEGORY_TYPE_MONTH";
    }
}