import java.util.Scanner;

class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String errorMessage) {
        super(errorMessage);
    }
}

class Account {
    private double balance;

    public Account(double initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Начальный баланс не может быть отрицательным");
        }
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Сумма депозита не может быть отрицательной");
        }
        balance += amount;
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (balance < amount) {
            throw new InsufficientFundsException("Недостаточно средств. Текущий баланс: " + balance);
        }
        balance -= amount;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите начальный баланс: ");
        double initialBalance = scanner.nextDouble();
        Account account = new Account(initialBalance);

        System.out.print("Введите сумму депозита: ");
        double depositAmount = scanner.nextDouble();
        try {
            account.deposit(depositAmount);
            System.out.println("Пополнение прошло успешно. Новый баланс: " + account.getBalance());
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        System.out.print("Введите сумму вывода: ");
        double withdrawalAmount = scanner.nextDouble();
        try {
            account.withdraw(withdrawalAmount);
            System.out.println("Вывод средств прошел успешно. Новый баланс: " + account.getBalance());
        } catch (InsufficientFundsException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}