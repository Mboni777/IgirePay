package dao;

import model.Account;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccountDAO {

    public void createAccount(Account account) {

        String sql = """
                INSERT INTO accounts
                (customer_id, account_type, balance, pin)
                VALUES (?, ?, ?, ?)
                """;

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, account.getCustomerId());
            stmt.setString(2, account.getAccountType());
            stmt.setDouble(3, account.getBalance());
            stmt.setString(4, account.getPin());

            stmt.executeUpdate();

            System.out.println("Account created successfully.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}