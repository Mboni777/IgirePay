package dao;

import model.Customer;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerDAO {

    public void addCustomer(Customer customer) {

        String sql = """
                INSERT INTO customers
                (full_name, email, phone_number)
                VALUES (?, ?, ?)
                """;

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, customer.getFullName());
            stmt.setString(2, customer.getEmail());
            stmt.setString(3, customer.getPhoneNumber());

            stmt.executeUpdate();

            System.out.println("Customer added successfully.");

        } catch (SQLException e) {

            System.out.println("Error adding customer: "
                    + e.getMessage());
        }
    }
}
