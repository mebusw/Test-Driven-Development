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

}
