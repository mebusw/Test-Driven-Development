package exercise.parkinglot;

public class SmarterStrategy implements ParkingStrategy {

	@Override
	public void park(Buddy self) {
		int mostEmptyRateParkingLot = 0;
		for (int i = 0; i < self.parkingLots.size(); i++) {
			ParkingLot pl = self.parkingLots.get(i);
			if (pl.emptyRate() > self.parkingLots.get(mostEmptyRateParkingLot)
					.emptyRate()) {
				mostEmptyRateParkingLot = i;
			}
		}
		self.parkingLots.get(mostEmptyRateParkingLot).park();
	}

}
