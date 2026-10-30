package dao;

import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProcessedRequestDAO {

    public boolean referenceExists(String referenceId) {

        String sql =
                "SELECT * FROM processed_requests WHERE reference_id = ?";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, referenceId);

            ResultSet rs = stmt.executeQuery();

            return rs.next();

        } catch (SQLException e) {

            System.out.println(e.getMessage());

            return false;
        }
    }

    public void saveReference(String referenceId) {

        String sql = """
                INSERT INTO processed_requests
                (reference_id)
                VALUES (?)
                """;

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, referenceId);

            stmt.executeUpdate();

            System.out.println("Reference stored successfully.");

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
    }
}