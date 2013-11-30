package exercise.money;

public class Money implements IMoney {
	public String currency;
	public int amount;

	public Money(int amount, String currency) {
		this.amount = amount;
		this.currency = currency;
	}

	@Override
	public boolean equals(Object other) {
		Money m = (Money) other;
		return this.amount == m.amount && this.currency.equals(m.currency);
	}

	@Override
	public String toString() {
		return this.amount + " " + this.currency;
	}

	@Override
	public IMoney add(Money addend) {
		if (this.currency.equals(addend.currency))
			return new Money(this.amount + addend.amount, this.currency);
		return new MoneyBag(this, addend);
	}

	@Override
	public IMoney add(MoneyBag moneyBag) {
		MoneyBag newMB = moneyBag.clone();
		return newMB.add(this);
	}
}
