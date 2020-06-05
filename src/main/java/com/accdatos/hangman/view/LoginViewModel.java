/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accdatos.hangman.view;

import com.accdatos.hangman.dao.UserDAO;
import com.accdatos.hangman.model.User;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Models the state of the Login View
 *
 * @author Ivan
 */
public class LoginViewModel {

    private final StringProperty userName = new SimpleStringProperty();
    private final StringProperty password = new SimpleStringProperty();
    private final ReadOnlyBooleanWrapper loginPossible = new ReadOnlyBooleanWrapper();
    private final StringProperty loginFeedback = new SimpleStringProperty();
    private UserDAO dao;

    /**
     * Initialization LoginViewModel
     */
    public LoginViewModel() {
        dao = new UserDAO();
        loginPossible.bind(userName.isNotEmpty().and(password.isNotEmpty()));
    }

    /**
     * Gets username property
     *
     * @return Username property
     */
    public StringProperty userNameProperty() {
        return userName;
    }

    /**
     * Gets passwordProperty
     *
     * @return passwordProperty
     */
    public StringProperty passwordProperty() {
        return password;
    }

    /**
     * Gets property loginFeedbackProperty
     *
     * @return loginFeedbackProperty
     */
    public StringProperty loginFeedbackProperty() {
        return loginFeedback;
    }

    /**
     * Gets property isLoginPossibleProperty
     *
     * @return isLoginPossibleProperty
     */
    public ReadOnlyBooleanProperty isLoginPossibleProperty() {
        return loginPossible.getReadOnlyProperty();
    }

    /**
     * Process login request
     *
     * @param userName username of credential to authenticate
     * @param password password of credential to authenticate
     * @return user if the credentials are correct or null otherwise
     */
    public User login(String userName, String password) {
        User user=dao.get(userName);
        if(user==null){
            user=new User(userName,password);
            dao.create(user);
        }
        return user;
//        if (user == null) {
//            user = new User(userName, password);
//            dao.create(user);
//        } else {
//            System.out.println(user.getHangmanRounds());
//            loginFeedback.set("UserName Found");
//            System.out.println("encontrado");
//        }
//        return user;
        //return new User(userName, password);

    }

}
