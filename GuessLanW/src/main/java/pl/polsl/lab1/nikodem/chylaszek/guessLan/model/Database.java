package pl.polsl.lab1.nikodem.chylaszek.guessLan.model;

import java.util.List;
import pl.polsl.lab1.nikodem.chylaszek.guessLan.jdbc.SaveResultsApp;
import pl.polsl.lab1.nikodem.chylaszek.guessLan.jdbc.SelectDataApp;
import pl.polsl.lab1.nikodem.chylaszek.guessLan.jdbc.ActivityLogApp;

/**
 * Represents the database for the game.
 * 
 * @author Nikodem
 * @version 1.1
 */
public class Database {
    
    private final List<Question> questions;
    private List<Player> results;
    private String lastName;
    
    private SaveResultsApp saveResult = new SaveResultsApp();
    private SelectDataApp selectDataApp = new SelectDataApp();
    private ActivityLogApp saveLog = new ActivityLogApp();

    /**
     * Initializes the database by loading questions and results from the database.
     */
    public Database() {
        this.questions = selectDataApp.getQuestionsFromDatabase();
        this.results = selectDataApp.loadResults(); 
    }

    /**
     * Saves a player's result in the database.
     *
     * @param player The player whose result should be saved.
     */
    public void saveResult(Player player) {
        saveResult.insertResult(player.getName(), player.getScore());
    }
    
     /**
     * Logs an activity in the database.
     *
     * @param player  The player from the activity.
     * @param action  The action being logged.
     * @param details Additional details of the action.
     */
    public void saveLog(Player player,  String action, String details) {
        saveLog.logActivity(player.getName(), action, details);
    }

    /**
     *
     * @return
     */
    public List<Player> getResults() {
        return results;
    }

    /**
     *
     * @return
     */
    public List<Question> getQuestions() {
        return questions;
    }
    
    
}
