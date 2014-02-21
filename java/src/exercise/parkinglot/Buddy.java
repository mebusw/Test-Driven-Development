package exercise.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class Buddy implements Parkable {
	protected List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
	protected ParkingStrategy strategy;
	public List<Buddy> subordinates = new ArrayList<Buddy>();
	private String name ;

	public Buddy(String name, ParkingStrategy strategy) {
		this.strategy = strategy;
		this.name =name;
	}

	public void takecare(ParkingLot parkingLot) {
		this.parkingLots.add(parkingLot);
	}

	public void park() {
		strategy.park(this);
	}

	protected void printOwnLots(StringBuffer result) {
		if (!parkingLots.isEmpty()) {
			result.append(this.name + "'s\n");
		}
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

	public int stat() {
		int sum = parkingLots.size();
		for (Buddy sub : subordinates) {
			sum += sub.stat();
		}
		return sum;
	}

}
