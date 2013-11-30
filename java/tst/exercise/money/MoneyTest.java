package exercise.money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
	private Money f12CHF;
	private Money f14CHF;
	private MoneyBag mb_12CHF_14CHF;
	private Money f7USD;
	private Money f21USD;
	private MoneyBag mb_14CHF_21USD;

	@Before
	public void setUp() throws Exception {
		f12CHF = new Money(12, "CHF");
		f14CHF = new Money(14, "CHF");
		mb_12CHF_14CHF = new MoneyBag(f12CHF, f14CHF);
		f7USD = new Money(7, "USD");
		f21USD = new Money(21, "USD");
		mb_14CHF_21USD = new MoneyBag(f14CHF, f21USD);
	}

	@Test
	public void testEquals() {
		assertNotNull(f12CHF);
		assertEquals(f12CHF, f12CHF);
		assertNotEquals(f12CHF, f14CHF);
		assertNotEquals("", f14CHF);
		assertEquals(new Money(12, "CHF"), f12CHF);
	}

	@Test
	public void test_same_currency_money_add_then_return_money() {
		assertEquals(new Money(26, "CHF"), f12CHF.add(f14CHF));
		assertEquals(new Money(12, "CHF"), f12CHF);
	}

	@Test
	public void testBagEquals() {
		assertEquals(mb_12CHF_14CHF, mb_12CHF_14CHF);
		assertEquals(mb_14CHF_21USD, new MoneyBag(f14CHF, f21USD));
		assertNotEquals(mb_14CHF_21USD, mb_12CHF_14CHF);
	}

	@Test
	public void test_different_currency_money_add_then_return_bag() {
		assertEquals(mb_14CHF_21USD, f14CHF.add(f21USD));
		assertEquals(new MoneyBag(f14CHF, f21USD), mb_14CHF_21USD);
	}

	@Test
	public void test_bag_add_money_with_another_currency_then_return_bigger_bag() {
		Money f5RMB = new Money(5, "RMB");
		assertEquals(new MoneyBag(f14CHF, f21USD, f5RMB),
				mb_14CHF_21USD.add(f5RMB));
		assertEquals(new MoneyBag(f14CHF, f21USD), mb_14CHF_21USD);
	}

	@Test
	public void test_bag_add_money_with_exist_currency_then_return_same_size_bag() {
		Money f28USD = new Money(28, "USD");
		assertEquals(new MoneyBag(f14CHF, f28USD), mb_14CHF_21USD.add(f7USD));
	}

	@Test
	public void test_bag_minus_money_with_exist_currency_then_simplify_bag_to_money() {
		assertEquals(f14CHF, mb_14CHF_21USD.add(new Money(-21, "USD")));
		assertEquals(new MoneyBag(f14CHF, f21USD), mb_14CHF_21USD);
	}

	@Test
	public void test_money_add_bag_with_another_currency_then_return_bag() {
		Money f5RMB = new Money(5, "RMB");
		assertEquals(new MoneyBag(f14CHF, f21USD, f5RMB),
				f5RMB.add(mb_14CHF_21USD));
	}

	@Test
	public void test_money_add_bag_with_exist_currency_then_return_same_size_bag() {
		Money f28USD = new Money(28, "USD");
		assertEquals(new MoneyBag(f14CHF, f28USD), f7USD.add(mb_14CHF_21USD));
	}

	@Test
	public void test_bag_add_bag_with_same_currency_then_return_smaller_bag() {
		MoneyBag mb_12CHF_7USD = new MoneyBag(f12CHF, f7USD);
		Money f40CHF = new Money(26, "CHF");
		Money f28USD = new Money(28, "USD");
		assertEquals(new MoneyBag(f40CHF, f28USD),
				mb_12CHF_7USD.add(mb_14CHF_21USD));
	}

	@Test
	public void test_resolve_to_a_certain_currency() {
		Money f4USD = new Money(4, "USD");
		Money f24RMB = new Money(24, "RMB");
		Exchanger exchanger = new Exchanger();
		exchanger.addRate("USD", "RMB", 6);

		assertEquals(f24RMB, f4USD.resolveTo("RMB", exchanger));

	}
}
