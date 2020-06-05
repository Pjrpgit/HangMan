package com.accdatos.hangman;

import com.accdatos.hangman.view.LoginView;
import org.apache.logging.log4j.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

/**
 * JavaFX App
 */
public class App extends Application {

    /**
     * App to boost Hangman game
     *
     * @param primaryStage Stage initial window
     */
    @Override
    public void start(Stage primaryStage) {
        Logger logger = LogManager.getLogger();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            LoginView loginView = new LoginView();
            fxmlLoader.setController(loginView);
            Parent parent = (Parent) fxmlLoader.load();
            Scene scene = new Scene(parent);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            logger.log(Level.FATAL, "Fatal error. Application can not start ...");
            System.exit(1);
        }
    }

    @Override
    public void stop() throws Exception {
        super.stop(); //To change body of generated methods, choose Tools | Templates.
        
    }
    
    

    /**
     * Main
     *
     * @param args Args to launch the application
     */
    public static void main(String[] args) {
        launch();
    }

}
