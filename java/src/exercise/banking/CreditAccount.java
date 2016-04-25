package exercise.banking;


import java.net.ConnectException;

public class CreditAccount extends Account {
    protected static IAssessmentService service = new IAssessmentService.AssessmentService();
    public long credit;

    public CreditAccount(IAssessmentService service) {
        try {
            this.credit = service.getCredit("123");
        } catch (ConnectException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void withdraw(long amount) {
        boolean insufficientFunds = getBalance() + credit < amount;
        if (insufficientFunds) {
            throw new InsufficientFundsException();
        }
        super.withdraw(amount);
    }
}
