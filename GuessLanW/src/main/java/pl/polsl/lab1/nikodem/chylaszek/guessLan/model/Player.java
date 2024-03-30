/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.lab1.nikodem.chylaszek.guessLan.model;

/**
 * Represents the Player in the game. 
 * 
 * @author Nikodem
 * @version 1.1
 */
public class Player {
    
    private int score;    // Player's score
    private String name;    // Player's name
    private int lives;

    /**
     * Initializes the Player .
     * 
     * @param n
     */
    public Player(String n) {
        name = n;
        score = 0;     // Initial score is 0
        lives = 3;
    }    
    
    /**
     *
     * @return
     */
    public int getLives() {
        return lives;
    }

    /**
     *
     * @param lives
     */
    public void setLives(int lives) {
        this.lives = lives;
    }
    
    /**
     *
     * @return
     */
    public int getScore() {
        return score;
    }
    
    /**
     *
     * @param score
     */
    public void setScore(int score) {
        this.score = score;
    }
    
    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Increases the player's score.
     * @param points number of which we would like to raise the score
     */
    public void increaseScore(int points) {
        this.score += points;
    }
    
    /**
     * Method decrementates number of lives.
     */
    public void loseLife(){
        lives--;
    }

    
    
    
    
    
    
    
}
