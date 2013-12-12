package exercise.bunStore;

public class BunStore {

	public Bun orderQF(String bunName) {
		Bun bun = new QFCooker().make(bunName);
		return bun;
	}

	public Bun orderGBL(String bunName) {
		Bun bun = new GBLCooker().make(bunName);
		return bun;

	}

}

class Cooker {
	protected String style;

	public Bun make(String bunName) {
		Bun bun = new Bun();
		mix(bun, bunName);
		kneadAndWrap(bun, bunName);
		steamAndDishOut(bun);
		return bun;
	}

	public void kneadAndWrap(Bun bun, String bunStyle) {
		bun.content.append(String.format(
				"Kneaded into dough in %s style. Wrapped buns in %s style. ",
				style, style));
	}

	public void mix(Bun bun, String bunName) {
		bun.content.append(String.format(
				"Mixed stuffing of %s bun in %s style. ", bunName, style));
	}

	private void steamAndDishOut(Bun bun) {
		bun.content.append("Steamed buns. Dished out buns.");
	}
}

class QFCooker extends Cooker {
	public QFCooker() {
		style = "QF";
	}

}

class GBLCooker extends Cooker {
	public GBLCooker() {
		style = "GBL";
	}
}

class Bun {
	protected StringBuffer content = new StringBuffer();

	public String getDesc() {
		return content.toString();
	}
}