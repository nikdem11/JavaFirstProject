/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.lab1.nikodem.chylaszek.guessLan.model;

import java.util.List;
import java.util.Random;

/**
 * The GameEngine class is responsible for managing the game logic.
 * 
 * @author Nikodem
 * @version 1.1
 */
public class GameEngine {

    private final List<Question> questions;
    private Player player;
    private Question currentQuestion;
    private boolean hintUsed;
    
    /**
     * Constructor, initializes the game with a set of questions.
     * @param database The database with list of questions.
     */
    public GameEngine(Database database) {
        this.questions = database.getQuestions();
    }

    /**
     * Starts a new game session for the given player.
     * @param playerName The name of the player starting the game.
     */
    public void startNewGame(String playerName) {
        this.player = new Player(playerName);
        this.currentQuestion = null;
        this.hintUsed = false;
    }
    
    /**
     *
     * @return
     */
    public Question getCurrentQuestion() {
            return currentQuestion;
        }
    
    /**
     * Selects a new random question from the list and sets it as the current question.
     * @return The new current question.
     */
    public Question getNextQuestion() {
        Random rand = new Random();
        currentQuestion = questions.get(rand.nextInt(questions.size()));
        hintUsed = false;
        return currentQuestion;
    }

    /**
     * Checks the player's answer against the current question's correct answer.
     * Score addition based on usinig/not using hint.
     * @param answer The player's answer to the current question.
     * @return True if the answer is correct, false otherwise.
     */
    public boolean checkAnswer(String answer) {
        if (player == null) {
            throw new IllegalStateException("Game not started. Player is null.");
        }
        if (currentQuestion != null && currentQuestion.getLanguage().equalsIgnoreCase(answer)) {
            player.increaseScore(hintUsed ? 1 : 3); // 1 point with hint, 3 without
            return true;
        }
        player.loseLife();
        return false;
    }

    /**
     *
     * @return
     */
    public Player getPlayer() {
        return player;
    }
    
    /**
     *
     */
    public void useHint() {
        hintUsed = true;
    }

    /**
     *
     * @return
     */
    public boolean isGameStarted() {
        return player != null;
    }
        
    /**
     *
     * @return
     */
    public boolean isHintUsed() {
        return hintUsed;
    }

    /**
     * Get the hint for the current question.
     * @return The hint string or an empty string if no current question.
     */
    public String getCurrentHint() {
        return currentQuestion != null ? currentQuestion.getHint() : "";
    }


}
