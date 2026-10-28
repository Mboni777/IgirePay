package Lab3;

import Lab1.Transaction;
import Lab2.TransactionDAO;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ReportService {
    private TransactionDAO transactionDAO = new TransactionDAO();

    public void exportToCSV(int accountId, String filepath) throws SQLException, IOException {
        List<Transaction> list = transactionDAO.getHistoryByAccount(accountId);
        try (FileWriter writer = new FileWriter(filepath)) {
            writer.write("TransactionID,AccountID,ReferenceID,Type,Amount,Timestamp\n");
            for (Transaction tx : list) {
                writer.write(tx.getId() + "," + tx.getAccountId() + "," + tx.getReferenceId() + "," +
                        tx.getTransactionType() + "," + tx.getAmount() + "," + tx.getCreatedAt() + "\n");
            }
        }
    }
}
