package exercise.bunStore;

public class BunStore {

	private StuffingSource stuffingSource;

	public BunStore() {
	}

	public Bun orderQF(String bunType) {
		return new Waiter().make(CookQF.make(bunType, stuffingSource));
	}

	public Bun orderGBL(String bunType) {
		return new Waiter().make(CookGBL.make(bunType, stuffingSource));
	}

	public void setStuffingSource(StuffingSource stuffingSource) {
		this.stuffingSource = stuffingSource;
	}

}

class Bun {
	public StringBuffer sb = new StringBuffer();

	public String toString() {
		return sb.toString();
	}

}

// ////////// Abstract Factory
interface StuffingSource {
	public Bun mix(Bun bun, String stuffing, String style);

}

class ManualStuffing implements StuffingSource {
	public Bun mix(Bun bun, String stuffing, String style) {
		bun.sb.append(String.format("Mixed stuffing of %s bun in %s style.",
				stuffing, style));
		return bun;
	}
}

class FactoryStuffing implements StuffingSource {

	public Bun mix(Bun bun, String stuffing, String style) {
		bun.sb.append(String
				.format("Got mixed stuffing of %s bun from %s Ingredient Factory. Got flour from %s Ingredient Factory. Prepared stuffing.",
						stuffing, style, style));
		return bun;

	}

}

// //////////////
class BunCooker {
	protected Bun bun;
	protected String style = "===";
	protected StuffingSource stuffingSource;
	protected String stuffing = ">>>";

	public void setStuffingSource(StuffingSource stuffingSource) {
		this.stuffingSource = stuffingSource;
	}

	public BunCooker() {
		bun = new Bun();
	}

	public Bun kneadAndWrap() {
		bun.sb.append(String.format(
				" Kneaded into dough in %s style. Wrapped buns in %s style. ",
				style, style));
		return bun;
	}

	public Bun mix() {
		stuffingSource.mix(bun, stuffing, style);
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
		stuffing = "Green Onion and Pork";
	}
}

class SSP_QFBunCooker extends QFBunCooker {
	public SSP_QFBunCooker() {
		stuffing = "Sam Sum and Pork";
	}

}

class SSS_QFBunCooker extends QFBunCooker {
	public SSS_QFBunCooker() {
		stuffing = "Su Sam Sun";
	}
}

class P_GBLBunCooker extends GBLBunCooker {
	public P_GBLBunCooker() {
		stuffing = "Pork";
	}
}

// ///////////
// // Simple Factory

class CookGBL {
	public static Bun make(String bunType, StuffingSource stuffingSource) {
		Bun bun = null;
		BunCooker bunCooker = null;

		if (bunType.equals("Pork bun"))
			bunCooker = new P_GBLBunCooker();

		bunCooker.setStuffingSource(stuffingSource);
		bun = bunCooker.mix();
		bun = bunCooker.kneadAndWrap();
		return bun;
	}
}

class CookQF {
	public static Bun make(String bunType, StuffingSource stuffingSource) {
		Bun bun;
		BunCooker bunCooker = null;
		if (bunType.equals("Green Onion and Pork bun")) {
			bunCooker = new GOP_QFBunCooker();
		} else if (bunType.equals("Sam Sun and Pork bun")) {
			bunCooker = new SSP_QFBunCooker();
		} else {
			bunCooker = new SSS_QFBunCooker();
		}

		bunCooker.setStuffingSource(stuffingSource);
		bun = bunCooker.mix();
		bun = bunCooker.kneadAndWrap();

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