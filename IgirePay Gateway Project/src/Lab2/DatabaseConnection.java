package Lab2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // ADJUST THESE STRINGS TO MATCH YOUR LOCAL POSTGRES CREDENTIALS EXACTLY
    private static final String URL = "jdbc:postgresql://localhost:5432/igirepay_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "DB_PASSWORD"; // Replace with your password

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("PostgreSQL JDBC Driver not found. Add it to your project class path.", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
