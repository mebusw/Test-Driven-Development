package exercise.money;

public interface IMoney {

	public IMoney add(Money addend);

	public abstract IMoney add(MoneyBag moneyBag);

}