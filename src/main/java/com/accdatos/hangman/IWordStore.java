/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accdatos.hangman;

/**
 * Interface for store of words
 *
 * @author Ivan
 */
public interface IWordStore {

    /**
     * Get word from store
     *
     * @return word
     */
    public String getWord();
}
