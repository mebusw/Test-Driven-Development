package exercise.bunStore;

public class BunStore {

	public String order(String bunType) {
		return new FollowUpWork().make(RawMateriaWork.make(bunType));
	}

}

class Bun {
	protected String process;
	
	public String toString() {
		return process;
	}
}

class QFBun extends Bun {
	protected String style = "QF";
	public String toString() {
		return process;
	}
}

class GOP_QFBun extends QFBun {
	public GOP_QFBun() {
		process = String.format("Mixed stuffing of Green Onion and Pork bun in %s style.", "QF");
	}
}

class SSP_QFBun extends QFBun {
	public SSP_QFBun() {
		process = "Mixed stuffing of Sam Sum and Pork bun in QF style.";
	}
}

class SSS_QFBun extends QFBun {
	public SSS_QFBun() {
		process = "Mixed stuffing of Su Sam Sun bun in QF style.";
	}
}

class RawMateriaWork {
	public static Bun make(String bunType) {
		Bun bun;
		if (bunType.equals("Green Onion and Pork bun"))
			bun = new GOP_QFBun();
		else if (bunType.equals("Sam Sun and Pork bun"))
			bun = new SSP_QFBun();
		else
			bun = new SSS_QFBun();

		return bun;

	}
}

class FollowUpWork {
	public String make(Bun bun) {
		return bun
				.toString()
				.concat(" Kneaded into dough. Wrapped buns. Steamed buns. Dished out buns.");
	}
}