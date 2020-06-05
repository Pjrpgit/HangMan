/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accdatos.hangman.view;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Ivan
 */
public class HangmanRoundRow {

    private SimpleIntegerProperty id;
    private SimpleStringProperty uncoveredWord;
    private SimpleIntegerProperty numErrors;

    HangmanRoundRow(Integer id, String uncoveredWord, int numErrors) {
        this.id = new SimpleIntegerProperty(id);
        this.uncoveredWord = new SimpleStringProperty(uncoveredWord);
        this.numErrors = new SimpleIntegerProperty(numErrors);
    }

    /**
     *
     * @return
     */
    public Integer getId() {
        return id.get();
    }

    /**
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id.set(id);
    }

    /**
     *
     * @return
     */
    public String getUncoveredWord() {
        return uncoveredWord.get();
    }

    /**
     *
     * @param uncoveredWord
     */
    public void setUncoveredWord(String uncoveredWord) {
        this.uncoveredWord.set(uncoveredWord);
    }

    /**
     *
     * @return
     */
    public Integer getNumErrors() {
        return numErrors.get();
    }

    /**
     *
     * @param numErrors
     */
    public void setNumErrors(Integer numErrors) {
        this.numErrors.set(numErrors);
    }
    

}
