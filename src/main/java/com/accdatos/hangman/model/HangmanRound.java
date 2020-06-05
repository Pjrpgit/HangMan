/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accdatos.hangman.model;

import com.accdatos.hangman.IWordStore;
import com.accdatos.hangman.dao.UserDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Models a hangman round
 *
 * @author Ivan
 */
@Entity
@Table(name="hangmanround")
public class HangmanRound {
    
    private static int genId = 0; // Manage a sequence to generate unique values in the round
    private final static int MAXNUMERRORS = 5;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private int numErrors;
    private int maxNumErrors = MAXNUMERRORS;
    private String hiddenWord;
    private String uncoveredWord;
    @OneToMany(fetch=FetchType.EAGER,mappedBy="roundid",cascade=CascadeType.ALL,orphanRemoval = true)
    private List<Guess> guesses;
    private Boolean finished;
    @ManyToOne
    private User username;  

    public HangmanRound() {
    }

    
    /**
     * Initialization routine
     *
     * @param wordStore Word store from which extract a word for the round
     */
    public HangmanRound(IWordStore wordStore) {
        init(wordStore);
    }

    /**
     * Gets Id
     *
     * @return Hangman round identifier
     */
    public int getId() {
        return id;
    }

    /**
     * Sets Id
     *
     * @param id Hangman round identifier
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets number of errors in the round
     *
     * @return Number of errors
     */
    public int getNumErrors() {
        return numErrors;
    }

    /**
     * Sets number of errors in the round
     *
     * @param numErrors Number of errors
     */
    public void setNumErrors(int numErrors) {
        this.numErrors = numErrors;
    }

    /**
     * Gets maximum number of errors allowed in the round
     *
     * @return Maximum number of errors
     */
    public int getMaxNumErrors() {
        return maxNumErrors;
    }

    /**
     * Sets maximum number of errors allowed in the round
     *
     * @param maxNumErrors Maximum number of errors
     */
    public void setMaxNumErrors(int maxNumErrors) {
        this.maxNumErrors = maxNumErrors;
    }

    /**
     * Gets secret word of the round
     *
     * @return Secret word
     */
    public String getHiddenWord() {
        return hiddenWord;
    }

    /**
     * Sets secret word of the round
     *
     * @param hiddenWord Secret word
     */
    public void setHiddenWord(String hiddenWord) {
        this.hiddenWord = hiddenWord;
    }

    /**
     * Gets uncovered word during the round
     *
     * @return
     */
    public String getUncoveredWord() {
        return this.uncoveredWord;
    }

    /**
     * Sets uncovered word during the round
     *
     * @param uncoveredWord Uncovered word
     */
    public void setUncoveredWord(String uncoveredWord) {
        this.uncoveredWord = uncoveredWord;
    }

    /**
     * Gets list of guesses made during the round
     *
     * @return List of guesses
     */
    public List<Guess> getGuesses() {
        return guesses;
    }

    /**
     * Gets list of letters entered during the round
     *
     * @return List of characters
     */
    public List<Character> getGuessedLetters() {
        return guesses.stream().map(guess -> guess.getLetter()).collect(Collectors.toList());
//        List<Character> result = new ArrayList();
//        for (Guess g : guesses) {
//            result.add(g.getLetter());
//        }
//        return result;
    }

    /**
     * Sets list of guesses made during the round
     *
     * @param guesses List of guesses
     */
    public void setGuesses(List<Guess> guesses) {
        this.guesses = guesses;
    }

    public User getUsername() {
        return username;
    }

    public void setUsername(User username) {
        this.username = username;
    }
    
    

    /**
     * Add guess to list of guesses made during the round
     *
     * @param guess Guess to be added
     */
    public void addGuess(Guess guess) {
        guess.setRoundid(this);
        //this.guesses.add(guess);
    }

    /**
     * Tells if the round is finished or not
     *
     * @return Truth value
     */
    public Boolean getFinished() {
        return finished;
    }

    /**
     * Sets a truth value that represents wheter the round is finished or not
     *
     * @param end Truth value
     */
    public void setFinished(Boolean end) {
        this.finished = end;
    }

    /**
     * Initialization procedure
     *
     * @param wordStore Word store to extract a secret word
     */
    private void init(IWordStore wordStore) {
//        this.setId(genId);
//        genId++;
        this.setHiddenWord(wordStore.getWord());
        System.out.println(this.getHiddenWord());
        this.setUncoveredWord("_".repeat(this.getHiddenWord().length()));
        this.guesses = new ArrayList<>();
        this.setNumErrors(0);
        this.setMaxNumErrors(MAXNUMERRORS);
        this.setFinished(false);
    }

    /**
     * Process a new guessed letter in the round
     *
     * @param letter Letter to be processed
     */
    public void processGuess(Character letter) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.getUncoveredWord().length(); i++) {
            if (letter == this.getHiddenWord().charAt(i)) {
                sb.append(letter);
            } else {
                sb.append(this.getUncoveredWord().charAt(i));
            }
        }
        if (this.getUncoveredWord().equals(sb.toString())) {
            this.setNumErrors(this.getNumErrors() + 1);
        }
        this.setUncoveredWord(sb.toString());
        Guess guess = new Guess(letter, this.getUncoveredWord());
        this.addGuess(guess);

        if (isEnd()) {
            this.setFinished(true);
        }
        UserDAO dao=new UserDAO();
        dao.mergeGuess(guess);
    }

    /**
     * Tells whether the whole word has been uncovered
     *
     * @return Truth value
     */

    private Boolean isWholeWordUncovered() {
        return !(this.getUncoveredWord().contains("_"));
    }

    /**
     * Tells whether the round is ended
     *
     * @return Truth value
     */
    public Boolean isEnd() {
        return (this.isWholeWordUncovered() || (this.getNumErrors() == this.getMaxNumErrors()));
    }

    /**
     * Tells whether the round has been won by the player
     *
     * @return Truth value
     */
    public Boolean isWin() {
        return this.isWholeWordUncovered();
    }

    @Override
    public String toString() {
        return "HangmanRound{" + "id=" + id + ", numErrors=" + numErrors + ", maxNumErrors=" + maxNumErrors + ", hiddenWord=" + hiddenWord + ", uncoveredWord=" + uncoveredWord + ", guesses=" + guesses + ", finished=" + finished + '}';
    }

    
    
    

}
