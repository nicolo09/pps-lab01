package example.model;

/**
 * This class represent a particular instance of a BankAccount.
 * In particular, a Simple Bank Account allows always the deposit
 * while the withdrawal is allowed only if the balance greater or equal the withdrawal amount
 */
public class SimpleBankAccount implements BankAccount {


    private double balance;
    private final AccountHolder holder;
    private final double fee;

    public SimpleBankAccount(final AccountHolder holder, final double balance, final double fee) {
        this.holder = holder;
        this.balance = balance;
        this.fee = fee;
    }

    @Override
    public AccountHolder getHolder() {
        return this.holder;
    }

    @Override
    public double getBalance() {
        return this.balance;
    }

    @Override
    public void deposit(final int userID, final double amount) {
        if (checkUser(userID)) {
            this.balance += amount;
        }
    }

    @Override
    public void withdraw(final int userID, final double amount) {
        if (!isWithdrawAllowed(amount)) {
            throw new IllegalArgumentException("The withdrawal amount is not allowed");
        }
        if (!checkUser(userID)) {
            throw new IllegalArgumentException("The user is not allowed to withdraw from this account");
        } else {
            this.balance -= (amount + fee);
        }
    }

    private boolean isWithdrawAllowed(final double amount) {
        return this.balance >= (amount - fee);
    }

    private boolean checkUser(final int id) {
        return this.holder.getId() == id;
    }
}
