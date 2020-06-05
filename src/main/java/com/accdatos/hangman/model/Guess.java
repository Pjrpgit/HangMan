/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accdatos.hangman.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Models each letter entered in a game round
 *
 * @author Ivan
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Guess.findAll", query = "SELECT g FROM Guess g")})
public class Guess implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private Character letter;
    private String currentUncoveredWord;
    @ManyToOne
    private HangmanRound roundid;

    public Guess() {
    }

    /**
     * Initialization routine
     *
     * @param letter The letter entered in the game
     * @param currentUncoveredWord State of the uncovered word at the time the
     * letter was guessed
     */
    public Guess(char letter, String currentUncoveredWord) {
        this.letter = letter;
        this.currentUncoveredWord = currentUncoveredWord;
    }

    /**
     * Gets the letter
     *
     * @return Letter guessed
     */
    public Character getLetter() {
        return letter;
    }

    /**
     * Sets the letter
     *
     * @param letter Letter guessed
     */
    public void setLetter(Character letter) {
        this.letter = letter;
    }

    /**
     * Gets the state of the uncovered word
     *
     * @return
     */
    public String getCurrentUncoveredWord() {
        return currentUncoveredWord;
    }

    /**
     * Sets the state of the uncovered word
     *
     * @param currentUncoveredWord Uncovered word
     */
    public void setCurrentUncoveredWord(String currentUncoveredWord) {
        this.currentUncoveredWord = currentUncoveredWord;
        System.out.println(currentUncoveredWord);
    }

    public HangmanRound getRoundid() {
        return roundid;
    }

    public void setRoundid(HangmanRound roundid) {
        this.roundid = roundid;
    }

    public int getId() {
        return id;
    }
    
    

    @Override
    public String toString() {
        return "Guess{" + "id=" + id + ", letter=" + letter + ", currentUncoveredWord=" + currentUncoveredWord + '}';
    }
    
    
}
