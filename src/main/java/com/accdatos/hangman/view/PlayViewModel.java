/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accdatos.hangman.view;

import com.accdatos.hangman.IWordStore;
import com.accdatos.hangman.WordStoreFactory;
import com.accdatos.hangman.dao.UserDAO;
import com.accdatos.hangman.model.Guess;
import com.accdatos.hangman.model.HangmanRound;
import com.accdatos.hangman.model.User;
import java.util.List;
import java.util.stream.Collectors;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Models the state of PlayView
 *
 * @author Ivan
 */
public class PlayViewModel {

    private User currentUser;
    private HangmanRound currentHangmanRound;
    private Integer currentHangmanRoundId;
    private UserDAO dao;
    private final StringProperty guessedLetters = new SimpleStringProperty();
    private final IntegerProperty numErrors = new SimpleIntegerProperty();
    private final StringProperty uncoveredWord = new SimpleStringProperty();
    private final BooleanProperty isOngoingRound = new SimpleBooleanProperty();
    private final BooleanProperty isWin = new SimpleBooleanProperty();
    private final StringProperty roundLog = new SimpleStringProperty();

    /**
     * Initialization
     */
    public PlayViewModel() {
        isOngoingRound.set(false);
        guessedLetters.set("");
        uncoveredWord.set("");
        numErrors.set(0);
        isWin.set(false);
        isOngoingRound.set(false);
        roundLog.set("");
        dao = new UserDAO();
    }

    /**
     * Gets getRoundLogProperty
     *
     * @return getRoundLogProperty
     */
    public StringProperty getRoundLogProperty() {
        return roundLog;
    }

    /**
     * Gets current hangman round id
     *
     * @return Current hangman round id
     */
    public Integer getCurrentHangmanRoundId() {
        return currentHangmanRoundId;
    }

    /**
     * Sets current hangman round id
     *
     * @param currentHangmanRoundId
     */
    public void setCurrentHangmanRoundId(Integer currentHangmanRoundId) {
        this.currentHangmanRoundId = currentHangmanRoundId;
    }

    /**
     * Gets current hangman round
     *
     * @return Current hangman round
     */
    public HangmanRound getCurrentHangmanRound() {
        return currentHangmanRound;
    }

    /**
     * Sets current hangman round
     *
     * @param currentHangmanRound
     */
    public void setCurrentHangmanRound(HangmanRound currentHangmanRound) {
        this.currentHangmanRound = currentHangmanRound;
    }

    /**
     * Sets current user
     *
     * @param user Current user
     */
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    /**
     * Process playGame request
     *
     */
    public void playGame() {
        WordStoreFactory wordStoreFactory = new WordStoreFactory();
        IWordStore wordStore = wordStoreFactory.getStore();
        currentHangmanRound = new HangmanRound(wordStore);
        //añado una linea para establecer la relacion
        currentHangmanRound.setUsername(currentUser);
        dao.createHangmanRound(currentHangmanRound);
        currentUser.addHangmanRound(currentHangmanRound);
        uncoveredWord.set(currentHangmanRound.getUncoveredWord());
        numErrors.set(currentHangmanRound.getNumErrors());
        isOngoingRound.set(true);
        guessedLetters.set(currentHangmanRound.getGuessedLetters().stream()
                .map(String::valueOf)
                .collect(Collectors.joining()));
        //dao.mergeHangmanRound(currentHangmanRound);
        //dao.mergeUser(currentUser);
        //dao.save(currentHangmanRound);
        //dao.save(currentUser);
    }

    /**
     * Process resumen game request
     *
     * @param id Hangman round id to be resumed
     */
    public void resumeGame(int id) {
        currentHangmanRound = currentUser.getHangmanRoundById(id);
        uncoveredWord.set(currentHangmanRound.getUncoveredWord());
        numErrors.set(currentHangmanRound.getNumErrors());
        isOngoingRound.set(true);
        guessedLetters.set("");
        guessedLetters.set(currentHangmanRound.getGuessedLetters().stream()
                .map(String::valueOf)
                .collect(Collectors.joining()));
    }

    /**
     * Process guess request
     *
     * @param letter Letter of guess to be processed
     */
    public void processGuess(char letter) {
        currentHangmanRound.processGuess(letter);
        uncoveredWord.set(currentHangmanRound.getUncoveredWord());
        guessedLetters.set(currentHangmanRound.getGuessedLetters().stream()
                .map(String::valueOf)
                .collect(Collectors.joining()));
        numErrors.set(currentHangmanRound.getNumErrors());
        isOngoingRound.set(!(currentHangmanRound.isEnd()));
        isWin.set(currentHangmanRound.isWin());
        //dao.save(currentUser);
        dao.mergeHangmanRound(currentHangmanRound);
        //dao.mergeUser(currentUser);
        
    }

    /**
     * Gets guessedLettersProperty
     *
     * @return guessedLettersProperty
     */
    public StringProperty guessedLettersProperty() {
        return guessedLetters;
    }

    /**
     * Gets numErrorsProperty
     *
     * @return numErrorsProperty
     */
    public IntegerProperty numErrorsProperty() {
        return numErrors;
    }

    /**
     * Gets isOngoingRoundProperty
     *
     * @return isOngoingRoundProperty
     */
    public BooleanProperty isOngoingRoundProperty() {
        return isOngoingRound;
    }

    /**
     * Gets isWinProperty
     *
     * @return isWinProperty
     */
    public BooleanProperty isWinProperty() {
        return isWin;
    }

    /**
     * Gets uncoveredWordProperty
     *
     * @return uncoveredWordProperty
     */
    public StringProperty uncoveredWordProperty() {
        String word = "";
        if (currentHangmanRound != null) {
            word = currentHangmanRound.getUncoveredWord();
        }
        uncoveredWord.set(word);
        return uncoveredWord;
    }

    /**
     * Gets list of guesses in hangman round
     *
     * @return List of guesses
     */
    public List<Guess> getGuesses() {
        return currentHangmanRound.getGuesses();
    }

    /**
     * Gets round summary log
     *
     * @return Summary log of the round
     */
    public String getRoundLog() {
        return (currentHangmanRound.getGuesses().stream().map(guess -> guess.getLetter().toString().concat("\t\t").concat(guess.getCurrentUncoveredWord()).concat("\n")).reduce("Round Summary \n", (element1, element2) -> element1.concat(element2))).concat("\nNumber of Errors = " + currentHangmanRound.getNumErrors());
    }

}
