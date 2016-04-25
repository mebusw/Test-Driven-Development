package exercise.banking;

import org.junit.Test;

import java.net.ConnectException;

import static junit.framework.TestCase.assertEquals;

public class AccountTest implements IAssessmentService {

    @Test
    public void GIVEN_insufficient_funds_WHEN_withdraw_THEN_throw_exception_AND_keep_balance_() throws Exception {
        Account account = Account.openDebitAccount();
        account.deposit(100);
        try {
            account.withdraw(200);
        } catch (InsufficientFundsException e) {
            assertEquals(100, account.getBalance());
        }
    }

    @Test
    public void deposit() throws Exception {
        Account account = Account.openDebitAccount();

        account.deposit(100);

        assertEquals(100, account.getBalance());
    }

    @Test
    public void withdraw_for_sufficient_funds() throws Exception {
        Account account = Account.openDebitAccount();
        account.deposit(100);

        account.withdraw(60);

        assertEquals(40, account.getBalance());
    }

    @Test
    public void withdraw_from_account_can_be_negative() throws Exception {
        Account account = Account.openAccount();
        account.deposit(100);

        account.withdraw(200);

        assertEquals(-100, account.getBalance());
    }

    @Test
    public void new_debit_account_deposit() {
        //given
        Account debitAccount = new DebitAccount();

        //when
        debitAccount.deposit(100);

        //then
        assertEquals(100, debitAccount.getBalance());
    }

    @Test
    public void new_debit_account_withdraw_for_sufficient_funds() {
        //given
        Account debitAccount = Account.openDebitAccount();
        debitAccount.deposit(100);

        //when
        debitAccount.withdraw(60);

        //then
        assertEquals(40, debitAccount.getBalance());
    }

    @Test
    public void new_debit_account_withdraw_for_insufficient_funds() {
        //given
        Account debitAccount = Account.openDebitAccount();
        debitAccount.deposit(100);


        try {
            //when
            debitAccount.withdraw(200);
        } catch (InsufficientFundsException e) {
            //then
            assertEquals(100, debitAccount.getBalance());
        }
    }

    @Test
    public void new_credit_card_withdraw_can_overdraw_within_credit() {
        Account account = new CreditAccount(this);
        //assertEquals(500, account.credit);
        account.deposit(100);

        account.withdraw(600);

        assertEquals(-500, account.getBalance());
    }

    @Test
    public void new_credit_card_withdraw_can_not_over_credit() {
        Account account = new CreditAccount(this);
        account.deposit(100);

        try {
            account.withdraw(800);
        } catch (InsufficientFundsException e) {
            assertEquals(100, account.getBalance());
        }
    }

    @Override
    public long getCredit(String socialSecurityNumber) throws ConnectException {
        return 500;
    }
}