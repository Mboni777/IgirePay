package service;

import model.Transaction;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReportService {

    public void exportTransactionsToCSV(List<Transaction> transactions) {

        try (FileWriter writer =
                     new FileWriter("reports/transactions.csv")) {

            writer.write("ID,Reference ID,Amount,Type,Timestamp\n");

            for (Transaction transaction : transactions) {

                writer.write(
                        transaction.getId() + "," +
                        transaction.getReferenceId() + "," +
                        transaction.getAmount() + "," +
                        transaction.getTransactionType() + "," +
                        transaction.getTimestamp() + "\n"
                );
            }

            System.out.println("CSV exported successfully.");

        } catch (IOException e) {

            System.out.println(e.getMessage());
        }
    }
}