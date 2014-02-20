package exercise.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class Buddy {
	protected List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();

	public void manage(ParkingLot parkingLot) {
		this.parkingLots.add(parkingLot);
	}

	public void park() {
		for (ParkingLot pl : parkingLots) {
			if (pl.remainingLots() > 0) {
				pl.park();
				return;
			}
		}
	}

	public void print(StringBuffer result) {
		printOwnLots(result);
	}

	protected void printOwnLots(StringBuffer result) {
		if (!parkingLots.isEmpty()) {
			result.append(this.getClass().toString() + "'s\n");
		}
	}

	public int stat() {
		return parkingLots.size();
	}

}
