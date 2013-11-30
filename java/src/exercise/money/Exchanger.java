package exercise.money;

import java.util.HashMap;
import java.util.Map;

public class Exchanger {

	private Map<String, Integer> rates = new HashMap<String, Integer>();

	public void addRate(String localCurrency, String foriegnCurrency,
			int rateOfForiegnToLocal) {
		this.rates.put(foriegnCurrency, rateOfForiegnToLocal);
	}

	public int get(String foriegnCurrency) {
		return rates.get(foriegnCurrency);
	}

}
