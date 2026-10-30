package service;

import dao.AccountDAO;
import dao.ProcessedRequestDAO;

public class PaymentService {

    private final AccountDAO accountDAO =
            new AccountDAO();

    private final ProcessedRequestDAO processedRequestDAO =
            new ProcessedRequestDAO();

    public void deposit(
            int accountId,
            double amount,
            String referenceId) {

        if (processedRequestDAO.referenceExists(referenceId)) {

            System.out.println(
                    "Duplicate transaction detected. Rejected."
            );

            return;
        }

        double balance =
                accountDAO.getBalance(accountId);

        balance += amount;

        accountDAO.updateBalance(
                accountId,
                balance
        );

        processedRequestDAO.saveReference(referenceId);

        System.out.println(
                "Deposit successful. New balance: "
                        + balance
        );
    }

    public void withdraw(
            int accountId,
            double amount,
            String referenceId) {

        if (processedRequestDAO.referenceExists(referenceId)) {

            System.out.println(
                    "Duplicate transaction detected. Rejected."
            );

            return;
        }

        double balance =
                accountDAO.getBalance(accountId);

        if (amount > balance) {

            System.out.println(
                    "Insufficient balance."
            );

            return;
        }

        balance -= amount;

        accountDAO.updateBalance(
                accountId,
                balance
        );

        processedRequestDAO.saveReference(referenceId);

        System.out.println(
                "Withdrawal successful. New balance: "
                        + balance
        );
    }
}