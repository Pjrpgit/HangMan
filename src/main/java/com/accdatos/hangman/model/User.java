/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accdatos.hangman.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Models a user of the game
 * 
 * @author Ivan
 */
@Entity
@Table(name="tbl_user")
@NamedQueries({
    @NamedQuery(name="User.findAll",query="Select u from User u"),
    @NamedQuery(name="User.findByUsername",query="Select u from User u where username = :username")
    })
public class User {
    
    @Id
    private String username;
    private String password;
    @OneToMany(fetch=FetchType.EAGER,mappedBy="username",cascade=CascadeType.ALL,orphanRemoval = true)//@OneToMany(mappedBy = "person", cascade = CascadeType.ALL, )
    private List<HangmanRound> hangmanRounds;

    public User() {
        
    }

    

    /**
     * Initialization routine
     * 
     * @param userName UserName of the user
     * @param password Password of the user
     */
    public User(String userName, String password) {
        this.username = userName;
        this.password = password;
        this.hangmanRounds = new ArrayList<>();
    }

    /**
     * Gets Username of the user
     * 
     * @return Username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets Username of the user
     * 
     * @param username Username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets password of the user
     * 
     * @return Password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password of the user
     * 
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets list of hangman rounds played by the user
     * 
     * @return List of hangman rounds
     */
    public List<HangmanRound> getHangmanRounds() {
        return hangmanRounds;
    }
    
    /**
     * Gets a hangman round with a particular identifier
     * 
     * @param id Identifier of the hangman round
     * @return Hangman round
     */
    public HangmanRound getHangmanRoundById(Integer id) {
//        return this.getHangmanRounds().stream().filter((hangmanRound) -> hangmanRound.getId() == id).collect(Collectors.toList()).get(0);
        HangmanRound hg=null;
        for(HangmanRound r :hangmanRounds){
            if(r.getId()==id)hg=r;
        }
        return hg;
    }

    /**
     * Sets list of hangman rounds played by the user
     * 
     * @param hangmanRounds List of hangman rounds
     */
    public void setHangmanRounds(List<HangmanRound> hangmanRounds) {
        this.hangmanRounds = hangmanRounds;
    }

    /**
     * Adds a hangman round to the list
     * 
     * @param hangmanRound Hangman round to be added
     */
    public void addHangmanRound(HangmanRound hangmanRound) {
        this.getHangmanRounds().add(hangmanRound);
        hangmanRound.setUsername(this);
    }

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", password=" + password + ", hangmanRounds=" + hangmanRounds + '}';
    }

    
}
