package pl.polsl.lab1.nikodem.chylaszek.guessLan.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import pl.polsl.lab1.nikodem.chylaszek.guessLan.model.Player;
import pl.polsl.lab1.nikodem.chylaszek.guessLan.model.Question;

/**
 * Retrieves data from the database.
 * 
 * @author Nikodem
 * @version 1.0
 */
public class SelectDataApp {

    /**
     * Gets questions from the database.
     *
     * @return A list of questions.
     */
    public List<Question> getQuestionsFromDatabase() {
        List<Question> questions = new ArrayList<>();
        try {
            Connection con = DatabaseConnectionManager.getInstance().getConnection();
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT q.question, q.hint, l.language FROM Questions q JOIN Languages l ON q.id_lan = l.id_lan");
            
            while (rs.next()) {
                String language = rs.getString("language");
                String questionText = rs.getString("question");
                String hint = rs.getString("hint");
                
                questions.add(new Question(language, questionText, hint));
            }
            rs.close();
        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
        return questions;
    }
    
    /**
     * Loads player results from the database.
     *
     * @return A list of player results.
     */
    public List<Player> loadResults() {
        List<Player> results = new ArrayList<>();
        try {
            Connection con = DatabaseConnectionManager.getInstance().getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT name, score FROM Results");
            while (rs.next()) {
                String name = rs.getString("name");
                int score = rs.getInt("score");
                Player player = new Player(name);
                player.setScore(score);
                results.add(player);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }
}
