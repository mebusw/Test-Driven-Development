package training.tele;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TeleTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void goldPlanWithOneLine() {
		Plan plan = new GoldPlan(1);
		assertEquals(49.95, plan.billing(), 0.01);
	}

	@Test
	public void goldPlanWithTwoLine() {
		Plan plan = new GoldPlan(2);
		assertEquals(14.5 + 49.95, plan.billing(), 0.01);
	}

	@Test
	public void silverPlanWithOneLine() {
		SilverPlan plan = new SilverPlan(1);
		assertEquals(29.95, plan.billing(), 0.01);
	}

	@Test
	public void silverPlanWithLine() {
		SilverPlan plan = new SilverPlan(3);
		assertEquals((2 * 21.5) + 29.95, plan.billing(), 0.01);
	}
}
