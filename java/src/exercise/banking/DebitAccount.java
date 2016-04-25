package exercise.banking;


public class DebitAccount extends Account {


    protected DebitAccount() {
        super();
    }

    public void withdraw(long amount) {
        boolean insufficientFunds = getBalance() < amount;
        if (insufficientFunds) {
            throw new InsufficientFundsException();
        }
        super.withdraw(amount);
    }
}
