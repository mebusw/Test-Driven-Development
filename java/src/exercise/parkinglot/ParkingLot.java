package exercise.parkinglot;

public class ParkingLot {

	private int lots;
	private int totalLots;

	public ParkingLot(int lots) {
		this.totalLots = lots;
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

	public int emptyRate() {
		return lots / totalLots;
	}

}
