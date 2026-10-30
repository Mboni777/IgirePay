package service;

import dao.ProcessedRequestDAO;

public class PaymentService {

    private final ProcessedRequestDAO processedRequestDAO =
            new ProcessedRequestDAO();

    public void deposit(
            double balance,
            double amount,
            String referenceId) {

        if (processedRequestDAO.referenceExists(referenceId)) {

            System.out.println(
                    "Duplicate transaction detected. Rejected."
            );

            return;
        }

        balance += amount;

        System.out.println(
                "Deposit successful. New balance: "
                        + balance
        );

        processedRequestDAO.saveReference(referenceId);
    }

    public void withdraw(
            double balance,
            double amount,
            String referenceId) {

        if (processedRequestDAO.referenceExists(referenceId)) {

            System.out.println(
                    "Duplicate transaction detected. Rejected."
            );

            return;
        }

        if (amount > balance) {

            System.out.println(
                    "Insufficient balance."
            );

            return;
        }

        balance -= amount;

        System.out.println(
                "Withdrawal successful. New balance: "
                        + balance
        );

        processedRequestDAO.saveReference(referenceId);
    }
}