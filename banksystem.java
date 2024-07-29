import java.util.*;

class Bank {
    private Map<String, Account> accounts;

    public Bank() {
        accounts = new HashMap<>();
    }

    public void createAccount(String name, double initialBalance) {
        if (accounts.containsKey(name)) {
            System.out.println("Account already exists for " + name);
        } else {
            accounts.put(name, new Account(name, initialBalance));
            System.out.println("Account created for " + name + " with initial balance " + initialBalance);
        }
    }

    public void deposit(String name, double amount) {
        Account account = accounts.get(name);
        if (account != null) {
            account.deposit(amount);
            System.out.println("Deposited " + amount + " to " + name + "'s account");
        } else {
            System.out.println("Account not found for " + name);
        }
    }

    public void withdraw(String name, double amount) {
        Account account = accounts.get(name);
        if (account != null) {
            if (account.getBalance() >= amount) {
                account.withdraw(amount);
                System.out.println("Withdrew " + amount + " from " + name + "'s account");
            } else {
                System.out.println("Insufficient funds for " + name);
            }
        } else {
            System.out.println("Account not found for " + name);
        }
    }

    public void transfer(String fromName, String toName, double amount) {
        Account fromAccount = accounts.get(fromName);
        Account toAccount = accounts.get(toName);

        if (fromAccount != null && toAccount != null) {
            if (fromAccount.getBalance() >= amount) {
                fromAccount.withdraw(amount);
                toAccount.deposit(amount);
                System.out.println("Transferred " + amount + " from " + fromName + " to " + toName);
            } else {
                System.out.println(fromName + " does not have enough balance to transfer " + amount);
            }
        } else {
            System.out.println("Account not found for one or both users");
        }
    }

    public void displayBalances() {
        for (Account account : accounts.values()) {
            System.out.println(account);
        }
    }
}

class Account {
    private String name ;
    private double balance;

    public Account(String name, double initialBalance) {
        this.name = name;
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            System.out.println("Insufficient funds for withdrawal");
        }
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }
}

public class BankAcc {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nChoose an action:");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer"); 
            System.out.println("5. Display Balances");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter account name: ");
                    String createName = scanner.nextLine();
                    System.out.print("Enter initial balance: ");
                    double initialBalance = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    bank.createAccount(createName, initialBalance);
                    break;
                case 2:
                    System.out.print("Enter account name: ");
                    String depositName = scanner.nextLine();
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    bank.deposit(depositName, depositAmount);
                    break;
                case 3:
                    System.out.print("Enter account name: ");
                    String withdrawName = scanner.nextLine();
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    bank.withdraw(withdrawName, withdrawAmount);
                    break;
                case 4:
                    System.out.print("Enter sender's account name: ");
                    String fromName = scanner.nextLine();
                    System.out.print("Enter receiver's account name: ");
                    String toName = scanner.nextLine();
                    System.out.print("Enter transfer amount: ");
                    double transferAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    bank.transfer(fromName, toName, transferAmount);
                    break;
                case 5:
                    bank.displayBalances();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
