/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accdatos.hangman.model;

import java.util.Objects;
import javax.persistence.Embeddable;

/**
 *
 * @author soad_
 */
public class GuessPK {
    private Character letter;
    private String currentUncoveredWord;

    public GuessPK() {
    }

    public Character getLetter() {
        return letter;
    }

    public String getCurrentUncoveredWord() {
        return currentUncoveredWord;
    }



    public void setLetter(Character letter) {
        this.letter = letter;
    }

    public void setCurrentUncoveredWord(String currentUncoveredWord) {
        this.currentUncoveredWord = currentUncoveredWord;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.letter);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GuessPK other = (GuessPK) obj;
        if (!Objects.equals(this.currentUncoveredWord, other.currentUncoveredWord)) {
            return false;
        }
        if (!Objects.equals(this.letter, other.letter)) {
            return false;
        }
        return true;
    }


    
}
