import example.model.AccountHolder;
import example.model.BankAccount;
import example.model.SimpleBankAccount;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the SimpleBankAccount implementation
 */
class SimpleBankAccountTest {

    private static final String HOLDER_NAME = "Mario";
    private static final String HOLDER_SURNAME = "Rossi";
    private static final int HOLDER_ID = 1;
    private static final int WRONG_HOLDER_ID = 2;
    private static final int STARTING_BALANCE = 0;
    private static final int DEPOSIT_AMOUNT = 100;
    private static final int WITHDRAW_AMOUNT = 30;
    private static final int WITHDRAW_FEE = 1;
    private AccountHolder accountHolder;
    private BankAccount bankAccount;

    @BeforeEach
    void beforeEach() {
        accountHolder = new AccountHolder(HOLDER_NAME, HOLDER_SURNAME, HOLDER_ID);
        bankAccount = new SimpleBankAccount(accountHolder, STARTING_BALANCE, WITHDRAW_FEE);
    }

    @Test
    void testInitialBalance() {
        assertEquals(STARTING_BALANCE, bankAccount.getBalance());
    }

    @Test
    void testDeposit() {
        bankAccount.deposit(accountHolder.getId(), DEPOSIT_AMOUNT);
        assertEquals(DEPOSIT_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testWrongDeposit() {
        bankAccount.deposit(accountHolder.getId(), DEPOSIT_AMOUNT);
        bankAccount.deposit(WRONG_HOLDER_ID, WITHDRAW_AMOUNT);
        assertEquals(DEPOSIT_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testWithdraw() {
        bankAccount.deposit(accountHolder.getId(), DEPOSIT_AMOUNT);
        bankAccount.withdraw(accountHolder.getId(), WITHDRAW_AMOUNT);
        assertEquals(DEPOSIT_AMOUNT - (WITHDRAW_AMOUNT + WITHDRAW_FEE), bankAccount.getBalance());
    }

    @Test
    void testWrongWithdraw() {
        bankAccount.deposit(accountHolder.getId(), DEPOSIT_AMOUNT);
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(WRONG_HOLDER_ID, WITHDRAW_AMOUNT));
        assertEquals(DEPOSIT_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testWithdrawMoreThanBalance() {
        bankAccount.deposit(accountHolder.getId(), DEPOSIT_AMOUNT);
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(accountHolder.getId(), DEPOSIT_AMOUNT + WITHDRAW_AMOUNT));
    }
}
