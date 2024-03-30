package pl.polsl.lab1.nikodem.chylaszek.guessLan.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Saves player results in the database.
 * 
 * @author Nikodem
 * @version 1.0
 */
public class SaveResultsApp {

    /**
     * Inserts a player's result into the database.
     *
     * @param playerName The name of the player.
     * @param score      The player's score.
     */

    public void insertResult(String playerName, int score) {
        String sql = "INSERT INTO Results (name, score) VALUES (?, ?)";
        try {
            Connection con = DatabaseConnectionManager.getInstance().getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, playerName);
            pstmt.setInt(2, score);
            pstmt.executeUpdate();
            System.out.println("Result inserted for player: " + playerName);
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
    }
}
