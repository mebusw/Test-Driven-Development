package exercise.parkinglot;


public class JuniorStrategy implements ParkingStrategy {

	@Override
	public void park(Buddy self) {
		for (ParkingLot pl : self.parkingLots) {
			if (pl.remainingLots() > 0) {
				pl.park();
				return;
			}
		}
	}

}
