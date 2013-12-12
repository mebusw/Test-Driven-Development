package exercise.bunStore;

public class BunStore {
	StuffingSource stuffingSource;

	public Bun orderQF(String bunName) {
		Bun bun = new QFCooker(stuffingSource).make(bunName);
		return bun;
	}

	public Bun orderGBL(String bunName) {
		Bun bun = new GBLCooker(stuffingSource).make(bunName);
		return bun;

	}

	public void setStuffingSource(StuffingSource stuffingSource) {
		this.stuffingSource = stuffingSource;
	}

}

interface StuffingSource {
	public String getSource(String bunStyle);
}

class ManualStuffing implements StuffingSource {

	@Override
	public String getSource(String bunStyle) {
		return String.format("Mixed stuffing of %s bun in %s style. ", "%s",
				bunStyle);
	}
}

class StuffingFactory implements StuffingSource {

	@Override
	public String getSource(String bunStyle) {
		return String
				.format("Got mixed stuffing of %s bun from %s Ingredient Factory. Got flour from %s Ingredient Factory. Prepared stuffing. ",
						"%s", bunStyle, bunStyle);
	}

}

class Cooker {
	protected String style;
	protected StuffingSource stuffingSource;

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
		bun.content.append(String.format(stuffingSource.getSource(style),
				bunName));
	}

	private void steamAndDishOut(Bun bun) {
		bun.content.append("Steamed buns. Dished out buns.");
	}
}

class QFCooker extends Cooker {
	public QFCooker(StuffingSource stuffingSource) {
		style = "QF";
		this.stuffingSource = stuffingSource;
	}

}

class GBLCooker extends Cooker {
	public GBLCooker(StuffingSource stuffingSource) {
		style = "GBL";
		this.stuffingSource = stuffingSource;
	}
}

class Bun {
	protected StringBuffer content = new StringBuffer();

	public String getDesc() {
		return content.toString();
	}
}