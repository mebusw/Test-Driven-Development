package exercise.bunStore;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.*;

import org.junit.experimental.theories.*;

@RunWith(Theories.class)
public class BunStoreTest {

	private BunStore store;

	@DataPoints
	public static String[] data() {
		return new String[] { new String("Monty Burns"),
				new String("Don Geiss"), new String("Arthur Jensen") };
	}

	@Theory
	public void theoryOligarchsHaveYachts(String suit) {
		assertThat(suit.toString(), containsString("n"));
	}

	@BeforeClass
	public static void setUp1() throws Exception {
		// System.out.println("ba");
	}

	@Before
	public void setUp() throws Exception {
		store = new BunStore();
		store.setStuffingSource(new ManualStuffing());
	}

	@Test
	public void test_QF_Green_Onion_and_Pork_bun() {
		Bun bun = store.orderQF("Green Onion and Pork bun");

		assertEquals(
				"Mixed stuffing of Green Onion and Pork bun in QF style. Kneaded into dough in QF style. Wrapped buns in QF style. Steamed buns. Dished out buns.",
				bun.toString());
	}

	@Test
	public void test_QF_Sam_Sun_and_Pork_bun() {
		Bun bun = store.orderQF("Sam Sun and Pork bun");

		assertEquals(
				"Mixed stuffing of Sam Sum and Pork bun in QF style. Kneaded into dough in QF style. Wrapped buns in QF style. Steamed buns. Dished out buns.",
				bun.toString());
	}

	@Test
	public void test_QF_Su_Sam_Sun_bun() {
		Bun bun = store.orderQF("Su Sam Sun bun");

		assertEquals(
				"Mixed stuffing of Su Sam Sun bun in QF style. Kneaded into dough in QF style. Wrapped buns in QF style. Steamed buns. Dished out buns.",
				bun.toString());
	}

	@Test
	public void test_GBL_Pork_bun() {
		Bun bun = store.orderGBL("Pork bun");

		assertEquals(
				"Mixed stuffing of Pork bun in GBL style. Kneaded into dough in GBL style. Wrapped buns in GBL style. Steamed buns. Dished out buns.",
				bun.toString());
	}

	@Test
	public void test_QF_Su_Sam_Sun_bun_From_Ingredient_Factory() {
		store.setStuffingSource(new FactoryStuffing());

		Bun bun = store.orderQF("Su Sam Sun bun");

		assertEquals(
				"Got mixed stuffing of Su Sam Sun bun from QF Ingredient Factory. Got flour from QF Ingredient Factory. Prepared stuffing. Kneaded into dough in QF style. Wrapped buns in QF style. Steamed buns. Dished out buns.",
				bun.toString());
	}

}
