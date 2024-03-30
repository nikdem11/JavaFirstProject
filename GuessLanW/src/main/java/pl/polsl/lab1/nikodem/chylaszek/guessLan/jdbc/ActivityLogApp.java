package pl.polsl.lab1.nikodem.chylaszek.guessLan.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents the Activity Log Application.
 * 
 * @author Nikodem
 * @version 1.0
 */
public class ActivityLogApp {

    /**
     * Logs an activity for a player.
     *
     * @param playerName The name of the player.
     * @param action     The action performed.
     * @param details    Additional details of the action.
     */
    public void logActivity(String playerName, String action, String details) {
        String sql = "INSERT INTO ActivityLog (name, action, timestamp, details) VALUES (?, ?, CURRENT_TIMESTAMP, ?)";
        try {
            Connection con = DatabaseConnectionManager.getInstance().getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, playerName);
            pstmt.setString(2, action);
            pstmt.setString(3, details);
            pstmt.executeUpdate();
            System.out.println("Log inserted.");
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
    }
    
    /**
     * Loads activity logs from the database.
     *
     * @return A list of activity logs.
     */
     public List<Map<String, Object>> loadLogs() {
        List<Map<String, Object>> logs = new ArrayList<>();
        String sql = "SELECT name, action, timestamp, details FROM ActivityLog ORDER BY timestamp DESC";
        try {
            Connection con = DatabaseConnectionManager.getInstance().getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Map<String, Object> logEntry = new HashMap<>();
                logEntry.put("name", rs.getString("name"));
                logEntry.put("action", rs.getString("action"));
                logEntry.put("timestamp", rs.getTimestamp("timestamp"));
                logEntry.put("details", rs.getString("details"));
                logs.add(logEntry);
            }
            rs.close();
        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
        return logs;
    }
}
