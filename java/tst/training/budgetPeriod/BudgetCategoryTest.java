package training.budgetPeriod;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BudgetCategoryTest {

    /*
    @Test
    public void testBudgetCategory(){
    try {
    BudgetCategoryType bct = ModelFactory.getBudgetCategoryType(BudgetCategoryTypes.BUDGET_CATEGORY_TYPE_MONTH);
    BudgetCategory bc = ModelFactory.createBudgetCategory("Test", bct, false);
    bc.setAmount(DateUtil.getDate(2007, Calendar.APRIL, 1), 100);
    bc.setAmount(DateUtil.getDate(2007, Calendar.MAY, 1), 200);
    bc.setAmount(DateUtil.getDate(2007, Calendar.JUNE, 1), 240);
    bc.setAmount(DateUtil.getDate(2007, Calendar.JULY, 1), 10);
    bc.setAmount(DateUtil.getDate(2007, Calendar.AUGUST, 1), 130);
    bc.setAmount(DateUtil.getDate(2007, Calendar.SEPTEMBER, 1), 13);
    bc.setAmount(DateUtil.getDate(2007, Calendar.OCTOBER, 1), 333);
    bc.setAmount(DateUtil.getDate(2007, Calendar.NOVEMBER, 1), 331);
    assertEquals((double) 100, bc.getAmount(DateUtil.getDate(2007, Calendar.APRIL, 1)), 1);
    assertEquals((double) 100, bc.getAmount(DateUtil.getDate(2007, Calendar.APRIL, 10)), 1);
    assertEquals((double) 100, bc.getAmount(DateUtil.getDate(2007, Calendar.APRIL, 28)), 1);
    assertEquals((double) 300, bc.getAmount(DateUtil.getDate(2007, Calendar.APRIL, 1), DateUtil.getDate(2007, Calendar.MAY, 31)), 1);
    assertEquals((double) 149, bc.getAmount(DateUtil.getDate(2007, Calendar.APRIL, 15), DateUtil.getDate(2007, Calendar.MAY, 15)), 1);
    }
    catch (Exception e){
    fail("Exception: " + e);
    }
    }
    @Test
    public void budgetPeriodWeekly() throws Exception {
    BudgetCategoryType bct = ModelFactory.getBudgetCategoryType(BudgetCategoryTypes.BUDGET_CATEGORY_TYPE_WEEK);
    BudgetCategory bc = ModelFactory.createBudgetCategory("Weekly Test", bct, false);
    bc.setAmount(DateUtil.getDate(2007, Calendar.SEPTEMBER, 10), 100);
    bc.setAmount(DateUtil.getDate(2007, Calendar.SEPTEMBER, 17), 100);
    bc.setAmount(DateUtil.getDate(2007, Calendar.SEPTEMBER, 24), 100);
    bc.setAmount(DateUtil.getDate(2007, Calendar.OCTOBER, 1), 100);
    bc.setAmount(DateUtil.getDate(2007, Calendar.OCTOBER, 8), 100);
    bc.setAmount(DateUtil.getDate(2007, Calendar.OCTOBER, 15), 100);
    bc.setAmount(DateUtil.getDate(2007, Calendar.OCTOBER, 22), 100);
    bc.setAmount(DateUtil.getDate(2007, Calendar.OCTOBER, 29), 100);
    bc.setAmount(DateUtil.getDate(2007, Calendar.NOVEMBER, 5), 100);
    bc.setAmount(DateUtil.getDate(2007, Calendar.NOVEMBER, 12), 100);
    assertEquals(100l,
    bc.getAmount(DateUtil.getDate(2007, Calendar.OCTOBER, 1)));
    assertEquals(14l,
    bc.getAmount(
    DateUtil.getDate(2007, Calendar.OCTOBER, 1),
    DateUtil.getDate(2007, Calendar.OCTOBER, 1)));
    assertEquals(28l,
    bc.getAmount(
    DateUtil.getDate(2007, Calendar.OCTOBER, 1),
    DateUtil.getDate(2007, Calendar.OCTOBER, 2)));
    assertEquals(100l,
    bc.getAmount(
    bct.getStartOfBudgetPeriod(DateUtil.getDate(2007, Calendar.OCTOBER, 1)),
    bct.getEndOfBudgetPeriod(DateUtil.getDate(2007, Calendar.OCTOBER, 1))));
    assertEquals(200l,
    bc.getAmount(
    bct.getStartOfBudgetPeriod(DateUtil.getDate(2007, Calendar.OCTOBER, 1)),
    bct.getEndOfBudgetPeriod(bct.getBudgetPeriodOffset(DateUtil.getDate(2007, Calendar.OCTOBER, 1), 1))));
    assertEquals(400l,
    bc.getAmount(
    bct.getStartOfBudgetPeriod(DateUtil.getDate(2007, Calendar.OCTOBER, 1)),
    bct.getEndOfBudgetPeriod(bct.getBudgetPeriodOffset(DateUtil.getDate(2007, Calendar.OCTOBER, 1), 3))));
    assertEquals(442l,
    bc.getAmount(
    DateUtil.getDate(2007, Calendar.OCTOBER, 1),
    DateUtil.getDate(2007, Calendar.OCTOBER, 31)));
    
    */
    
    /*
    // Add Tests
    @Test (expected=RuntimeException.class)
    public void testBeginDateIsLaterThanStartDateThrowsAnException() throws Exception {
    bc.getAmount(fourthOfJuly2011 , secondOfJuly2011);
    }
    @Test
    public void getAmountOfARangeInsideABudgetPeriod() throws Exception {
    bc.setAmount(secondOfJuly2011, 31);
    assertEquals(3, bc.getAmount(secondOfJuly2011, fourthOfJuly2011));
    }
    @Test
    public void getAmountOfARangeTwoNeigbouringBudgetPeriods() throws Exception {
    bc.setAmount(twentyJune2011, 300);
    bc.setAmount(secondOfJuly2011, 31);
    assertEquals(114, bc.getAmount(twentyJune2011, fourthOfJuly2011));
    }
    @Test
    public void getAmountOfARangeWithManyBudgetPeriodsInBetween() throws Exception {
    bc.setAmount(tenthMarch2011, 31);
    bc.setAmount(twentyApril2011, 3000);
    bc.setAmount(twentyMay2011, 3100);
    bc.setAmount(twentyJune2011, 3000);
    bc.setAmount(secondOfJuly2011, 31);
    assertEquals(9100 + 2 + 22, bc.getAmount(tenthMarch2011, secondOfJuly2011));
    }
    */
}
