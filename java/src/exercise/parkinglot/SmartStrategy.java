package exercise.parkinglot;

public class SmartStrategy implements ParkingStrategy {

	@Override
	public void park(Buddy self) {
		int mostEmptyParkingLot = 0;
		for (int i = 0; i < self.parkingLots.size(); i++) {
			ParkingLot pl = self.parkingLots.get(i);
			if (pl.remainingLots() > self.parkingLots.get(mostEmptyParkingLot)
					.remainingLots()) {
				mostEmptyParkingLot = i;
			}
		}
		self.parkingLots.get(mostEmptyParkingLot).park();

	}

}
