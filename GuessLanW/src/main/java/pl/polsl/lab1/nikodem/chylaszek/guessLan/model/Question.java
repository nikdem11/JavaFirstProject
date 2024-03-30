/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.lab1.nikodem.chylaszek.guessLan.model;

/**
 * Class which represents single Question in the game.
 * 
 * @author Nikodem
 * @version 1.1
 */
public class Question {
    
    private String language;
    private String text;
    private String hint;
    
    /**
     *
     * @param language
     * @param text
     * @param hint
     */
    public Question(String language, String text, String hint) {
        this.language = language;
        this.text = text;
        this.hint = hint;
    }

    /**
     *
     * @return
     */
    public String getLanguage() {
        return language;
    }

    /**
     *
     * @param language
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     *
     * @return
     */
    public String getText() {
        return text;
    }

    /**
     *
     * @param text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     *
     * @return
     */
    public String getHint() {
        return hint;
    }

    /**
     *
     * @param hint
     */
    public void setHint(String hint) {
        this.hint = hint;
    }
   
}
