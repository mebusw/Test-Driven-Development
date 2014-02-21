package exercise.parkinglot;

public class ParkingLot implements Parkable {

	private int emptyLots;
	private int totalLots;

	public ParkingLot(int lots) {
		this.totalLots = lots;
		this.emptyLots = lots;

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

	@Override
	public void print(StringBuffer result) {
		// TODO Auto-generated method stub
		
	}

}
