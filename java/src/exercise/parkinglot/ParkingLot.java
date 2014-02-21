package exercise.parkinglot;

public class ParkingLot {

	private int emptyLots;
	public int totalLots;
	public String name;

	public ParkingLot(String name, int lots) {
		this.totalLots = lots;
		this.emptyLots = lots;
		this.name = name;

	}

	public void park() {
		if (this.emptyLots > 0)
			this.emptyLots -= 1;
	}

	public int remainingLots() {
		return this.emptyLots;
	}

	public void getOut() {
		this.emptyLots += 1;
	}

	public double emptyRate() {
		return emptyLots * 1.0 / totalLots;
	}

}
