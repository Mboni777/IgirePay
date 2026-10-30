package dao;

import model.Transaction;
import util.DatabaseConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {

    public void addTransaction(Transaction transaction) {

        String sql = """
                INSERT INTO transactions
                (account_id, reference_id, transaction_type, amount)
                VALUES (?, ?, ?, ?)
                """;

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, transaction.getId());
            stmt.setString(2, transaction.getReferenceId());
            stmt.setString(3, transaction.getTransactionType());
            stmt.setDouble(4, transaction.getAmount());

            stmt.executeUpdate();

            System.out.println("Transaction saved successfully.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Transaction> getAllTransactions() {

        List<Transaction> transactions = new ArrayList<>();

        String sql = "SELECT * FROM transactions";

        try (
                Connection conn = DatabaseConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)
        ) {

            while (rs.next()) {

                Transaction transaction = new Transaction(
                        rs.getInt("id"),
                        rs.getString("reference_id"),
                        rs.getDouble("amount"),
                        rs.getString("transaction_type"),
                        rs.getTimestamp("created_at")
                                .toLocalDateTime()
                );

                transactions.add(transaction);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return transactions;
    }
}