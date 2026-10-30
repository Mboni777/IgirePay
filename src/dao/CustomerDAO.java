package dao;

import model.Customer;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
            System.out.println(e.getMessage());
        }
    }

    public List<Customer> getAllCustomers() {

        List<Customer> customers = new ArrayList<>();

        String sql = "SELECT * FROM customers";

        try (
                Connection conn = DatabaseConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)
        ) {

            while (rs.next()) {

                Customer customer = new Customer(
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getString("email"),
                        rs.getString("phone_number")
                );

                customers.add(customer);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return customers;
    }

    public Customer getCustomerById(int id) {

        String sql = "SELECT * FROM customers WHERE id = ?";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                return new Customer(
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getString("email"),
                        rs.getString("phone_number")
                );
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public void updateCustomer(Customer customer) {

        String sql = """
                UPDATE customers
                SET full_name = ?, email = ?, phone_number = ?
                WHERE id = ?
                """;

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, customer.getFullName());
            stmt.setString(2, customer.getEmail());
            stmt.setString(3, customer.getPhoneNumber());
            stmt.setInt(4, customer.getId());

            stmt.executeUpdate();

            System.out.println("Customer updated successfully.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteCustomer(int id) {

        String sql = "DELETE FROM customers WHERE id = ?";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, id);

            stmt.executeUpdate();

            System.out.println("Customer deleted successfully.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}