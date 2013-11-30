package exercise.money;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MoneyBag implements IMoney {
	public List<Money> moneys;

	public MoneyBag(Money... moneys) {
		this.moneys = new ArrayList<Money>(Arrays.asList(moneys));
	}

	@Override
	public boolean equals(Object other) {
		MoneyBag mb = (MoneyBag) other;

		return mb.moneys.equals(this.moneys);
	}

	@Override
	public String toString() {
		return "" + this.moneys;
	}

	@Override
	public MoneyBag clone() {
		MoneyBag newMb = new MoneyBag();
		newMb.moneys = new ArrayList<Money>(this.moneys);
		return newMb;
	}

	@Override
	public IMoney add(Money addend) {
		MoneyBag newMb = this.clone();
		for (Money m : newMb.moneys) {
			if (addend.currency.equals(m.currency)) {
				m.amount += addend.amount;
				if (0 == m.amount) {
					newMb.moneys.remove(m);
				}
				if (newMb.moneys.size() == 1) {
					return newMb.moneys.get(0);
				}
				return newMb;
			}
		}

		newMb.moneys.add(addend);
		return newMb;
	}

	@Override
	public IMoney add(MoneyBag moneyBag) {
		MoneyBag newMb = this.clone();
		for (Money m : moneyBag.moneys) {
			newMb.add(m);
		}
		return newMb;
	}
}
