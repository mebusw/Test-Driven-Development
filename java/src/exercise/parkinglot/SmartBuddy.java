package exercise.parkinglot;

public class SmartBuddy extends Buddy {
	public void park() {
		int mostEmptyParkingLot = 0;
		for (int i = 0; i < parkingLots.size(); i++) {
			ParkingLot pl = parkingLots.get(i);
			if (pl.remainingLots() > parkingLots.get(mostEmptyParkingLot)
					.remainingLots()) {
				mostEmptyParkingLot = i;
			}
		}
		parkingLots.get(mostEmptyParkingLot).park();
	}
}
