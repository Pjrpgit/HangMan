/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accdatos.hangman.view;

import com.accdatos.hangman.model.HangmanRound;
import com.accdatos.hangman.model.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Models the state of PickView
 * 
 * @author Ivan
 */
public class PickViewModel {

    private User currentUser;
    final ObservableList<HangmanRoundRow> hangmanRoundTableData;
    
    /**
     * Initialization
     */
    public PickViewModel() {

        hangmanRoundTableData = FXCollections.observableArrayList();
    }

    /**
     * Obtains the table data to fill the rounds table
     * 
     * @return Observable List of rounds
     */
    public ObservableList<HangmanRoundRow> getHangmanRoundTableData() {
        if (getCurrentUser() != null
                && getHangmanRounds() != null) {
            getHangmanRounds().stream().filter(
                    hangmanRound -> !(hangmanRound.isEnd())).forEach(hangmanRound -> {
                        HangmanRoundRow hangmanRoundRow = new HangmanRoundRow(hangmanRound.getId(), hangmanRound.getUncoveredWord(), hangmanRound.getNumErrors());
                        hangmanRoundTableData.add(hangmanRoundRow);
                    });

        }
        return hangmanRoundTableData;
    }

    /**
     * Gets current user
     * @return Current user
     */
    public User getCurrentUser() {
        return currentUser;
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
     * Gets list of rounds
     * @return List of rounds
     */
    public List<HangmanRound> getHangmanRounds() {
        //return currentUser.getHangmanRounds();
        HashMap<Integer,HangmanRound>hmp=new HashMap();
        for(HangmanRound r: currentUser.getHangmanRounds()){
            hmp.put(r.getId(), r);
        }
        return new ArrayList(hmp.values());
    }

}
