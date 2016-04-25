package exercise.banking;

public class Account {

    protected long balance;

    protected Account() {
    }

    public static Account openDebitAccount() {
        return new DebitAccount();
    }

    public static Account openAccount() {
        return new Account();
    }

    public long getBalance() {
        return balance;
    }

    public void deposit(long amount) {
        balance += amount;
    }

    public void withdraw(long amount) {
        balance -= amount;
    }
}
