package exercise.bunStore;

public class BunStore {

	public Bun orderQF(String bunType) {
		return new Waiter().make(CookQF.make(bunType));
	}

	public Bun orderGBL(String bunType) {
		return new Waiter().make(CookGBL.make(bunType));
	}

}

class Bun {
	public StringBuffer sb = new StringBuffer();

	public String toString() {
		return sb.toString();
	}

}

// //////////////

class BunCooker {

	protected Bun bun;
	protected String style = "===";

	public BunCooker() {
		super();
		bun = new Bun();
	}

	public Bun kneadAndWrap() {
		bun.sb.append(String.format(
				" Kneaded into dough in %s style. Wrapped buns in %s style. ",
				style, style));
		return bun;
	}

	public Bun mix(String stuffing) {
		bun.sb.append(String.format("Mixed stuffing of %s bun in %s style.",
				stuffing, style));
		return bun;
	}

}

class QFBunCooker extends BunCooker {
	public QFBunCooker() {
		style = "QF";
	}
}

class GBLBunCooker extends BunCooker {
	public GBLBunCooker() {
		style = "GBL";

	}
}

// /////////////////
// / Factory

class GOP_QFBunCooker extends QFBunCooker {

	public GOP_QFBunCooker() {
		mix("Green Onion and Pork");
	}
}

class SSP_QFBunCooker extends QFBunCooker {
	public SSP_QFBunCooker() {
		mix("Sam Sum and Pork");
	}

}

class SSS_QFBunCooker extends QFBunCooker {
	public SSS_QFBunCooker() {
		mix("Su Sam Sun");
	}
}

class P_GBLBunCooker extends GBLBunCooker {
	public P_GBLBunCooker() {
		mix("Pork");
	}
}

// ///////////
// // Simple Factory

class CookGBL {
	public static Bun make(String bunType) {
		Bun bun = null;

		if (bunType.equals("Pork bun"))
			bun = new P_GBLBunCooker().kneadAndWrap();
		return bun;

	}
}

class CookQF {
	public static Bun make(String bunType) {
		Bun bun;
		if (bunType.equals("Green Onion and Pork bun"))
			bun = new GOP_QFBunCooker().kneadAndWrap();
		else if (bunType.equals("Sam Sun and Pork bun"))
			bun = new SSP_QFBunCooker().kneadAndWrap();
		else
			bun = new SSS_QFBunCooker().kneadAndWrap();

		return bun;

	}
}

// ///////////

class Waiter {
	public Bun make(Bun bun) {
		bun.sb.append("Steamed buns. Dished out buns.");
		return bun;
	}
}