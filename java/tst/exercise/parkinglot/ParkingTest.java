package exercise.parkinglot;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * 练习1：基本停车场
 * 看看窗外，可能就有一个停车场。我们要实现一个停车场：可以把车停在场内，直到停满后不能再停车了。可以从停车场取车，这样可以让更多的车停进来。
 * 练习2：停车伙计 现在我们雇佣了一个停车伙计。伙计可以管理多个停车场，帮助客人停车取车。他停车的策略很简单，哪个停车场有空位就停在哪里。
 * 
 * 练习3：聪明停车伙计 停车场越来越多，原来一个伙计都不够用了，我们又雇佣了一个，这次的伙计比较聪明，他会判断哪个停车场的空位较多，然后把车停在那里
 * 练习4：更聪明停车伙计
 * 不多久，我们又雇佣了一个，而且是个大学生，他更聪明了，会判断哪个停车场的空位率较高，然后把车停在那里。举例来说，停车场A的10个车位被用了5个
 * ，停车场B的5个停车位被用了3个，他会停在A停车场。 练习5：停车经理
 * 生意越做越大，我们的伙计那么多，当然得招一个“职业经理人”了，MBA毕业的，他现在管着好几个停车伙计，当然同时也管着好几个停车场。他可以直接代客泊车
 * ，也可以把车交给某个伙计来停。 练习6：打印停车场和服务生
 * 我们到底有多少停车场了，是不是需要了解一下？找停车经理吧，他会随时给我们汇报。比如，他当前以及他手下伙计管理的所有停车场，打印出他们的名字和对应的停车场。
 * 练习7：打印停车场使用率 想起来了，除了伙计和停车场名字，我们还要另外一个报表，统计每个停车场的车辆总数。
 * 
 * @author jacky
 * 
 */
public class ParkingTest {

	private ParkingLot parkingLot;

	@Before
	public void setUp() throws Exception {
		parkingLot = new ParkingLot(10);
	}

	// //////////// #1 Parking Lot
	@Test
	public void testAnEmptyParkingLotCanParkACar() {
		parkingLot.park();
		assertEquals(10 - 1, parkingLot.remainingLots());
	}

	protected void parkMultiCars(ParkingLot parkingLot, int times) {
		for (int i = 0; i < times; i++) {
			parkingLot.park();
		}
	}

	@Test
	public void testParkingLotCanMultiCars() {
		parkMultiCars(parkingLot, 9);
		assertEquals(1, parkingLot.remainingLots());
		assertEquals(1.0 / 10, parkingLot.emptyRate(), 0.1);
	}

	@Test
	public void testAnAlmostFullParkingLotCanOnlyParkOneMoreCar() {
		parkMultiCars(parkingLot, 10);
		parkMultiCars(parkingLot, 1);
		assertEquals(0, parkingLot.remainingLots());

	}

	@Test
	public void testAFullParkingLotCanGetCarOut() {
		parkMultiCars(parkingLot, 10);
		parkingLot.getOut();

		assertEquals(1, parkingLot.remainingLots());
	}

	// ////////////// #2 Buddy
	@Test
	public void testABuddyCanParkTheCar() {
		Buddy buddy = new Buddy();
		buddy.manage(parkingLot);
		buddy.park();

		assertEquals(10 - 1, parkingLot.remainingLots());
	}

	@Test
	public void testABuddyCanParkTheCarToEmptyParkingLot() {
		Buddy buddy = new Buddy();
		buddy.manage(parkingLot);
		parkMultiCars(parkingLot, 10);
		ParkingLot anotherLot = new ParkingLot(5);
		buddy.manage(anotherLot);

		buddy.park();

		assertEquals(10 - 10, parkingLot.remainingLots());
		assertEquals(5 - 1, anotherLot.remainingLots());
	}

	// ////////////// #3 Smart Buddy
	@Test
	public void testASmartBuddyCanParkTheCar() {
		Buddy buddy = new SmartBuddy();
		buddy.manage(parkingLot);
		buddy.park();

		assertEquals(10 - 1, parkingLot.remainingLots());
	}

	@Test
	public void testASmartBuddyCanParkTheCarToMostEmptyLot() {
		Buddy buddy = new SmartBuddy();
		buddy.manage(parkingLot);
		parkMultiCars(parkingLot, 9);
		ParkingLot anotherLot = new ParkingLot(5);
		buddy.manage(anotherLot);

		buddy.park();

		assertEquals(10 - 9, parkingLot.remainingLots());
		assertEquals(5 - 1, anotherLot.remainingLots());
	}

	// ////////////// #4 Smarter Buddy
	@Test
	public void testASmarterBuddyCanParkTheCarToThatOfMostEmptyRate() {
		Buddy buddy = new SmarterBuddy();
		ParkingLot anotherLot = new ParkingLot(5);
		buddy.manage(anotherLot);
		parkMultiCars(anotherLot, 3);
		buddy.manage(parkingLot);
		parkMultiCars(parkingLot, 5);

		buddy.park();

		assertEquals(10 - 5 - 1, parkingLot.remainingLots());
		assertEquals(5 - 3, anotherLot.remainingLots());
	}

	// ////////////// #5 Manager
	@Test
	public void testAManagerCanParkTheCarToHisOwnLot() {
		Buddy manager = new Manager();
		manager.manage(parkingLot);
		parkMultiCars(parkingLot, 5);

		manager.park();

		assertEquals(10 - 5 - 1, parkingLot.remainingLots());
	}

	@Test
	public void testAManagerCanParkTheCarToLotOfHisBuddy() {
		Manager manager = new Manager();
		Buddy subordinate = new Buddy();
		manager.manage(subordinate);
		subordinate.manage(parkingLot);
		parkMultiCars(parkingLot, 5);

		manager.park();

		assertEquals(10 - 5 - 1, parkingLot.remainingLots());
	}

	// ////////////// #6 Print Parking Lots
	@Test
	public void testPrintOnlyOwnLots() {
		Manager manager = new Manager();
		manager.manage(parkingLot);
		StringBuffer result = new StringBuffer();

		manager.print(result);

		assertEquals("class exercise.parkinglot.Manager's\n", result.toString());
	}

	@Test
	public void testPrintOnlySubLots() {
		Manager manager = new Manager();
		Buddy buddy = new Buddy();
		buddy.manage(parkingLot);
		manager.manage(buddy);
		StringBuffer result = new StringBuffer();

		manager.print(result);

		assertEquals("class exercise.parkinglot.Buddy's\n", result.toString());
	}
	@Test
	public void testPrintBothOwnAndSubLots() {
		Manager manager = new Manager();
		manager.manage(new ParkingLot(5));
		Buddy buddy = new Buddy();
		buddy.manage(parkingLot);
		manager.manage(buddy);
		StringBuffer result = new StringBuffer();
		
		manager.print(result);
		
		assertEquals("class exercise.parkinglot.Manager's\nclass exercise.parkinglot.Buddy's\n", result.toString());
	}
}
