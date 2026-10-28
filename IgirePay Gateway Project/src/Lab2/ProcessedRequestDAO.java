package Lab2;

import java.sql.*;

public class ProcessedRequestDAO {
    public boolean isDuplicate(String referenceId) throws SQLException {
        String sql = "SELECT 1 FROM processed_requests WHERE reference_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, referenceId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // True if reference matches an item already in DB
            }
        }
    }

    public void registerReference(String referenceId) throws SQLException {
        String sql = "INSERT INTO processed_requests (reference_id) VALUES (?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, referenceId);
            ps.executeUpdate();
        }
    }
}
