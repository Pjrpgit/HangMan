module com.accdatos.hangman {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.persistence;
    requires org.apache.logging.log4j;
    exports com.accdatos.hangman;
    exports com.accdatos.hangman.view;
    exports com.accdatos.hangman.dao;
    exports com.accdatos.hangman.model;
}