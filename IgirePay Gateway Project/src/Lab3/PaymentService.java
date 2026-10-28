package Lab3;

import Lab1.*;
import Lab2.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;


public class PaymentService {
    private AccountDAO accountDAO = new AccountDAO();
    private TransactionDAO transactionDAO = new TransactionDAO();
    private ProcessedRequestDAO processedRequestDAO = new ProcessedRequestDAO();

    public synchronized void processDeposit(int accountId, double amount, String refId) throws SQLException, PaymentException {
        if (processedRequestDAO.isDuplicate(refId)) throw new PaymentException("Duplicate reference ID detected!");

        Account acc = accountDAO.read(accountId);
        if (acc == null) throw new PaymentException("Account not found.");

        acc.deposit(amount);

        // Execute updates atomicaly via manual Commit blocks if needed, keeping it straightforward here
        accountDAO.updateBalanceAndPin(acc);
        transactionDAO.create(accountId, refId, "DEPOSIT", amount);
        processedRequestDAO.registerReference(refId);
    }

    public synchronized void processWithdraw(int accountId, double amount, String pin, String refId) throws SQLException, PaymentException, SQLException {
        if (processedRequestDAO.isDuplicate(refId)) throw new PaymentException("Duplicate reference ID detected!");

        Account acc = accountDAO.read(accountId);
        if (acc == null) throw new PaymentException("Account not found.");
        if (!acc.getPin().equals(pin)) throw new PaymentException("Invalid operational security PIN.");

        acc.withdraw(amount);

        accountDAO.updateBalanceAndPin(acc);
        transactionDAO.create(accountId, refId, "WITHDRAW", amount);
        processedRequestDAO.registerReference(refId);
    }

    public synchronized void processTransfer(int sourceId, int targetId, double amount, String pin, String refId) throws SQLException, PaymentException {
        if (processedRequestDAO.isDuplicate(refId)) throw new PaymentException("Duplicate reference ID detected!");
        if (sourceId == targetId) throw new PaymentException("Cannot transfer within the same account.");

        Account source = accountDAO.read(sourceId);
        Account target = accountDAO.read(targetId);

        if (source == null || target == null) throw new PaymentException("Source or target account missing.");
        if (!source.getPin().equals(pin)) throw new PaymentException("Invalid PIN security verification.");

        source.withdraw(amount);
        target.deposit(amount);

        accountDAO.updateBalanceAndPin(source);
        accountDAO.updateBalanceAndPin(target);

        transactionDAO.create(sourceId, refId, "TRANSFER_OUT", amount);
        transactionDAO.create(targetId, refId, "TRANSFER_IN", amount);
        processedRequestDAO.registerReference(refId);
    }
}
