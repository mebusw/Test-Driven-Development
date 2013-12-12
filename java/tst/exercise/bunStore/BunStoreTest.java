package exercise.bunStore;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BunStoreTest {

	private BunStore store;

	@Before
	public void setUp() throws Exception {
		store = new BunStore();
		store.setStuffingSource(new ManualStuffing());
	}

	@Test
	public void test_QF_Green_Onion_and_Pork_bun() {
		Bun bun = store.orderQF("Green Onion and Pork");

		assertEquals(
				"Mixed stuffing of Green Onion and Pork bun in QF style. Kneaded into dough in QF style. Wrapped buns in QF style. Steamed buns. Dished out buns.",
				bun.getDesc());
	}

	@Test
	public void test_QF_Sam_Sum_and_Pork_bun() {
		Bun bun = store.orderQF("Sam Sum and Pork");

		assertEquals(
				"Mixed stuffing of Sam Sum and Pork bun in QF style. Kneaded into dough in QF style. Wrapped buns in QF style. Steamed buns. Dished out buns.",
				bun.getDesc());
	}

	@Test
	public void test_GBL_Pork_bun() {
		Bun bun = store.orderGBL("Pork");

		assertEquals(
				"Mixed stuffing of Pork bun in GBL style. Kneaded into dough in GBL style. Wrapped buns in GBL style. Steamed buns. Dished out buns.",
				bun.getDesc());
	}
	
	@Test
	public void test_gbl_Pork_bun_From_Gredient_Factory() {
		store.setStuffingSource(new StuffingFactory());
		Bun bun = store.orderGBL("Pork");

		assertEquals(
				"Got mixed stuffing of Pork bun from GBL Ingredient Factory. Got flour from GBL Ingredient Factory. Prepared stuffing. Kneaded into dough in GBL style. Wrapped buns in GBL style. Steamed buns. Dished out buns.",
				bun.getDesc());
	}
}
