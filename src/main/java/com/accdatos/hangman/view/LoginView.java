/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accdatos.hangman.view;

import com.accdatos.hangman.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class for LoginView
 *
 * @author Ivan
 */
public class LoginView {

    @FXML
    private TextField userName;
    @FXML
    private PasswordField password;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Text loginFeedback;

    private LoginViewModel loginViewModel;

    private User user;

    /*
    * Data Binding instructions
    */
    
    @FXML
    void initialize() {
        loginViewModel = new LoginViewModel();
        loginViewModel.userNameProperty().bind(userName.textProperty());
        loginViewModel.passwordProperty().bindBidirectional(password.textProperty());
        loginButton.disableProperty().bind(loginViewModel.isLoginPossibleProperty().not());        
        //hacer el  metodo en loginViewModel
        loginFeedback.textProperty().bind(loginViewModel.loginFeedbackProperty());
    }

    /**
     * Process Login request
     * 
     * @param event Login button click event
     */
    @FXML
    protected void processLogin(ActionEvent event) {
        try {
            if ((user = this.loginViewModel.login(userName.getText(), password.getText())) != null) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/play.fxml"));
                PlayView playView = new PlayView();
                playView.setCurrentUser(user);
                fxmlLoader.setController(playView);
                Parent parent = (Parent) fxmlLoader.load();
                Scene scene = new Scene(parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.setScene(scene);
                app_stage.show();
            }
        } catch (Exception e) {
            loginFeedback.setText("System Error. Can't login at this moment");
        }

    }
    @FXML
    protected void processDelete(ActionEvent event){
        System.out.println("Has pulsado Delete");
    }
    
    @FXML
    protected void processRegister(ActionEvent event){
         System.out.println("Has pulsado Delete");
    }

}
