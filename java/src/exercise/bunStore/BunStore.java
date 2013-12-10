package exercise.bunStore;

public class BunStore {

	public Bun order(String bunType) {
		if (bunType.equals("Green Onion and Pork bun"))
			return new GOPBun();
		else if (bunType.equals("Sam Sun and Pork bun"))
			return new SSPBun();
		return new SSSBun();
	}

}

class Bun {

}

class GOPBun extends Bun {
	public String toString() {
		return "Mixed stuffing of Green Onion and Pork bun. Kneaded into dough. Wrapped buns. Steamed buns. Dished out buns.";
	}
}

class SSPBun extends Bun {
	public String toString() {
		return "Mixed stuffing of Sam Sum and Pork bun. Kneaded into dough. Wrapped buns. Steamed buns. Dished out buns.";
	}
}

class SSSBun extends Bun {
	public String toString() {
		return "Mixed stuffing of Su Sam Sun bun. Kneaded into dough. Wrapped buns. Steamed buns. Dished out buns.";
	}
}