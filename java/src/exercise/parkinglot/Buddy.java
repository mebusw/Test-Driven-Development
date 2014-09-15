package exercise.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class Buddy {
	protected List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
	protected ParkingStrategy strategy;
	public List<Buddy> subordinates = new ArrayList<Buddy>();
	private String name;

	public Buddy(String name, ParkingStrategy strategy) {
		this.strategy = strategy;
		this.name = name;
	}

	public void takecare(ParkingLot parkingLot) {
		this.parkingLots.add(parkingLot);
	}

	public void park() {
		strategy.park(this);
	}

	public void manage(Buddy subordinate) {
		subordinates.add(subordinate);
	}

	public void print(StringBuffer result) {
		printOwnLots(result);
		for (Buddy sub : subordinates) {
			sub.print(result);
		}
	}

	protected void printOwnLots(StringBuffer result) {
		for (ParkingLot pl : parkingLots) {
			result.append(this.name + "'s " + pl.name + "\n");
		}
	}

	public void stat(StringBuffer result) {
		statOwnLots(result);
		for (Buddy sub : subordinates) {
			sub.stat(result);
		}
	}

	protected void statOwnLots(StringBuffer result) {
		for (ParkingLot pl : parkingLots) {
			result.append((pl.totalLots - pl.remainingLots()) + " in " + pl.name
					+ "\n");
		}
	}
}
