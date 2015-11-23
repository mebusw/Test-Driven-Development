package training.tele;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TeleTest {

	@Before
	public void setUp() throws Exception {
	}

	// #1
	@Test
	public void goldPlanWithOneLine() {
		Plan plan = new GoldPlan(1);
		assertEquals(49.95, plan.billing(), 0.01);
	}

	@Test
	public void goldPlanWithTwoLines() {
		Plan plan = new GoldPlan(2);
		assertEquals(14.5 + 49.95, plan.billing(), 0.01);
	}

	@Test
	public void silverPlanWithOneLine() {
		Plan plan = new SilverPlan(1);
		assertEquals(29.95, plan.billing(), 0.01);
	}

	@Test
	public void silverPlanWithTwoLines() {
		Plan plan = new SilverPlan(3);
		assertEquals((2 * 21.5) + 29.95, plan.billing(), 0.01);
	}

	// #2
	@Test
	public void goldPlanWithOneLineNotExceedMinutes() {
		Plan plan = new GoldPlan(1, 999);
		assertEquals(49.95, plan.billing(), 0.01);
	}

	@Test
	public void goldPlanWithOneLineExceedingOneMinute() {
		Plan plan = new GoldPlan(1, 1001);
		assertEquals(49.95 + 0.45, plan.billing(), 0.01);
	}

	@Test
	public void goldPlanWithOneLineExceedingTenMinutes() {
		Plan plan = new GoldPlan(1, 1010);
		assertEquals(49.95 + 10 * 0.45, plan.billing(), 0.01);
	}

	@Test
	public void silverPlanWithOneLineNotExceedMinutes() {
		Plan plan = new SilverPlan(1, 500);
		assertEquals(29.95, plan.billing(), 0.01);
	}

	@Test
	public void silverPlanWithOneLineExceedingTwentyMinutes() {
		Plan plan = new SilverPlan(1, 520);
		assertEquals(29.95 + 20 * 0.54, plan.billing(), 0.01);
	}

	// #3
	@Test
	public void goldPlanWithFourLines_shouldGetFamilyDiscount() {
		Plan plan = new GoldPlan(4);
		assertEquals(5 + 2 * 14.5 + 49.95, plan.billing(), 0.01);
	}

	@Test
	public void silverPlanWithFourLines_shouldGetFamilyDiscount() {
		Plan plan = new SilverPlan(5);
		assertEquals(2 * 5 + 2 * 21.5 + 29.95, plan.billing(), 0.01);
	}

	// #4

	@Test
	public void goldPlanWithFourLinesAndExceedingMinutes() {
		Plan plan = new GoldPlan(4, 1123);
		assertEquals(123 * 0.45 + 5 + 2 * 14.5 + 49.95, plan.billing(), 0.01);
	}

	@Test
	public void silverPlanWithFiveLinesAndExceedingMinutes() {
		Plan plan = new SilverPlan(5, 521);
		assertEquals(21 * 0.54 + 2 * 5 + 2 * 21.5 + 29.95, plan.billing(), 0.01);
	}
}
