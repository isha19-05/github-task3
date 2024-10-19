import java.util.Scanner;

public class ATMmachine {
    static class BankAccount {
        private double balance;

        public BankAccount() {
            this.balance = 0.0;
        }

        public double getBalance() {
            return balance;
        }

        public void deposit(double amount) {
            if (amount > 0) {
                balance += amount;
            }
        }

        public boolean withdraw(double amount) {
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                return true;
            }
            return false;
        }
    }

    static class ATM {
        private BankAccount account;

        public ATM(BankAccount account) {
            this.account = account;
        }

        public void deposit(double amount) {
            account.deposit(amount);
            System.out.printf("Successfully deposited $%.2f. Your new balance is $%.2f.%n", amount,
                    account.getBalance());
        }

        public void withdraw(double amount) {
            if (account.withdraw(amount)) {
                System.out.printf("Successfully withdrew $%.2f. Your new balance is $%.2f.%n", amount,
                        account.getBalance());
            } else {
                System.out.println("Insufficient balnace or invalid amount!");
            }
        }

        public void checkBalance() {
            System.out.printf("Your current balance is $%.2f.%n", account.getBalance());
        }

        public void start() {
            Scanner scanner = new Scanner(System.in);
            int choice;

            do {
                System.out.println("\nATM Menu:");
                System.out.println("1.Deposit");
                System.out.println("2.Withdraw");
                System.out.println("3.Check Balance");
                System.out.println("4.Exit");
                System.out.print("Select an option: ");

                while (!scanner.hasNextInt()) {
                    System.out.print("Invalid input. Please enter a number(1-4): ");
                    scanner.next();
                }
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = scanner.nextDouble();
                        if (depositAmount <= 0) {
                            System.out.println("Deposit amount must be positive!");
                        } else {
                            deposit(depositAmount);
                        }
                        break;

                    case 2:
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = scanner.nextDouble();
                        withdraw(withdrawAmount);
                        break;

                    case 3:
                        checkBalance();
                        break;

                    case 4:
                        System.out.println("Thank you for using ATM. Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } while (choice != 4);

            scanner.close();
        }
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount();
        ATM atm = new ATM(account);
        atm.start();
    }
}
