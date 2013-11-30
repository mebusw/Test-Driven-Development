package exercise.money;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MoneyBag implements IMoney {
	public List<Money> moneis;

	public MoneyBag(Money... moneys) {
		this.moneis = new ArrayList<Money>(Arrays.asList(moneys));
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof MoneyBag))
			return false;
		MoneyBag mb = (MoneyBag) other;
		return mb.moneis.equals(this.moneis);
	}

	@Override
	public String toString() {
		return "" + this.moneis;
	}

	@Override
	public MoneyBag clone() {
		MoneyBag newMb = new MoneyBag();
		newMb.moneis = new ArrayList<Money>(this.moneis);
		return newMb;
	}

	@Override
	public IMoney add(Money addend) {
		MoneyBag newMb = this.clone();
		for (Money m : newMb.moneis) {
			if (addend.currency.equals(m.currency)) {
				m.amount += addend.amount;
				removeZero(newMb, m);
				return newMb.simplify();
			}
		}

		newMb.moneis.add(addend);
		return newMb;
	}

	private void removeZero(MoneyBag newMb, Money m) {
		if (0 == m.amount) {
			newMb.moneis.remove(m);
		}
	}

	private IMoney simplify() {
		if (this.moneis.size() == 1) {
			return this.moneis.get(0);
		}
		return this;
	}

	@Override
	public IMoney add(MoneyBag moneyBag) {
		MoneyBag newMb = this.clone();
		for (Money m : moneyBag.moneis) {
			newMb.add(m);
		}
		return newMb;
	}
}
