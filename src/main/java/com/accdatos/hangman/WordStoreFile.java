/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accdatos.hangman;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

/**
 * Models a word store from a words file
 *
 * @author Ivan
 */
public class WordStoreFile implements IWordStore {

    List<String> palabras;

    /**
     * Initialization of the store
     * @param path Path to the words file
     * @throws Exception
     */
    public WordStoreFile(String path) throws Exception {
        Charset charset = Charset.forName("UTF-8");
        palabras = Files.readAllLines(Paths.get(getClass().getResource(path).toURI()), charset);
    }

    /**
     * Get word from the store
     * 
     * @return word
     */
    @Override
    public String getWord() {
        Random rand = new Random(); //instance of random class
        return palabras.get(rand.nextInt(palabras.size()));
    }

}
