package Lab2;

import Lab1.Account;
import Lab1.WalletAccount;
import Lab1.SavingsAccount;
import java.sql.*;

public class AccountDAO {
    public void create(Account account) throws SQLException {
        String sql = "INSERT INTO accounts (customer_id, account_type, balance, pin) VALUES (?, ?, ?, ?) RETURNING id";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, account.getCustomerId());
            ps.setString(2, account.getAccountType());
            ps.setDouble(3, account.getBalance());
            ps.setString(4, account.getPin());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Reflection update or internal value update simulation handled in service layer
                }
            }
        }
    }

    public Account read(int id) throws SQLException {
        String sql = "SELECT * FROM accounts WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String type = rs.getString("account_type");
                    if ("WALLET".equals(type)) {
                        return new WalletAccount(rs.getInt("id"), rs.getInt("customer_id"), rs.getDouble("balance"), rs.getString("pin"));
                    } else {
                        return new SavingsAccount(rs.getInt("id"), rs.getInt("customer_id"), rs.getDouble("balance"), rs.getString("pin"));
                    }
                }
            }
        }
        return null;
    }

    public void updateBalanceAndPin(Account account) throws SQLException {
        String sql = "UPDATE accounts SET balance = ?, pin = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, account.getBalance());
            ps.setString(2, account.getPin());
            ps.setInt(3, account.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM accounts WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
