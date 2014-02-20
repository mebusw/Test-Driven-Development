package exercise.parkinglot;

public class SmarterBuddy extends Buddy {
	public void park() {
		int mostEmptyRateParkingLot = 0;
		for (int i = 0; i < parkingLots.size(); i++) {
			ParkingLot pl = parkingLots.get(i);
			if (pl.emptyRate() > parkingLots.get(mostEmptyRateParkingLot)
					.emptyRate()) {
				mostEmptyRateParkingLot = i;
			}
		}
		parkingLots.get(mostEmptyRateParkingLot).park();
	}
}
