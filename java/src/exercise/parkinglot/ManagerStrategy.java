package exercise.parkinglot;

public class ManagerStrategy implements ParkingStrategy {

	@Override
	public void park(Buddy self) {
		Buddy m = (Buddy)self;
		if (self.parkingLots.size() > 0) {
			self.parkingLots.get(0).park();
			return;
		}
		m.subordinates.get(0).park();
	}
}
