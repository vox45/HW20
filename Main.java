public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000);

        // Creating multiple threads to perform transactions
        Thread thread1 = new Thread(new ATMTransaction(account, true, 500), "Thread 1");
        Thread thread2 = new Thread(new ATMTransaction(account, false, 200), "Thread 2");
        Thread thread3 = new Thread(new ATMTransaction(account, true, 300), "Thread 3");
        Thread thread4 = new Thread(new ATMTransaction(account, false, 400), "Thread 4");

        // Starting the threads
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        // Waiting for all threads to finish
        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Displaying final balance
        System.out.println("Final balance: " + account.getBalance());
    }
}