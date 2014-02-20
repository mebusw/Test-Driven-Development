package exercise.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class Manager extends Buddy {
	private List<Buddy> subordinates = new ArrayList<Buddy>();

	public void manage(Buddy subordinate) {
		subordinates.add(subordinate);
	}

	@Override
	public void park() {
		if (parkingLots.size() > 0) {
			parkingLots.get(0).park();
			return;
		}
		subordinates.get(0).park();
	}

	public void print(StringBuffer result) {
		printOwnLots(result);
		for (Buddy sub : subordinates) {
			sub.print(result);
		}
	}

}
