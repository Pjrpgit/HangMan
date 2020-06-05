package com.accdatos.hangman;

/**
 *
 * @author Ivan
 */
public class SystemInfo {

    /**
     *
     * @return
     */
    public static String javaVersion() {
        return System.getProperty("java.version");
    }

    /**
     *
     * @return
     */
    public static String javafxVersion() {
        return System.getProperty("javafx.version");
    }

}