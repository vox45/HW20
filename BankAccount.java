public class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public synchronized void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println(Thread.currentThread().getName() + " withdrew: " + amount);
        } else {
            System.out.println(Thread.currentThread().getName() + " cannot withdraw " + amount + ". Insufficient funds.");
        }
    }

    public synchronized void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println(Thread.currentThread().getName() + " deposited: " + amount);
        } else {
            System.out.println(Thread.currentThread().getName() + " cannot deposit " + amount + ". Invalid amount.");
        }
    }

    public double getBalance() {
        return balance;
    }
}

class ATMTransaction implements Runnable {
    private BankAccount account;
    private boolean isWithdrawal;
    private double amount;

    public ATMTransaction(BankAccount account, boolean isWithdrawal, double amount) {
        this.account = account;
        this.isWithdrawal = isWithdrawal;
        this.amount = amount;
    }

    @Override
    public void run() {
        if (isWithdrawal) {
            account.withdraw(amount);
        } else {
            account.deposit(amount);
        }
    }
}


