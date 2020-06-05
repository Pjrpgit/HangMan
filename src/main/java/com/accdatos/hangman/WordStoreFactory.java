/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accdatos.hangman;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Models word store factory
 *
 * @author Ivan
 */
public class WordStoreFactory {

    private static final Logger logger = LogManager.getLogger(WordStoreFactory.class);

    /**
     * Get word store
     *
     * @return word store
     */
    public IWordStore getStore() {
        IWordStore wordStore = null;
        try {
            wordStore = new WordStoreFile("/wordfiles/palabras.txt");
        } catch (Exception e) {
            logger.log(Level.FATAL, "Fatal error. Word store from file can not be created ...");
            System.exit(1);
        }
        return wordStore;
    }
}
