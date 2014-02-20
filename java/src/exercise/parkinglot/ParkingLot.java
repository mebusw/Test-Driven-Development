package exercise.parkinglot;

public class ParkingLot {

	private int lots;

	public ParkingLot(int lots) {
		this.lots = lots;
	}

	public void park() {
		if (this.lots > 0)
			this.lots -= 1;
	}

	public int remainingLots() {
		return this.lots;
	}

	public void getOut() {
		this.lots += 1;
	}

}
