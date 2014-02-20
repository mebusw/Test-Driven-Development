package exercise.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class Buddy {
	private List<ParkingLot> parkingLots;

	public void manage(ParkingLot parkingLot) {
		this.parkingLots = new ArrayList<ParkingLot>();
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

}
