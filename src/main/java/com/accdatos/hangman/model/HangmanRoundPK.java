///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.accdatos.hangman.model;
//
//import java.util.Objects;
//
///**
// *
// * @author soad_
// */
//public class HangmanRoundPK {
//    private int id;
//    private String username;
//
//    public HangmanRoundPK() {
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    @Override
//    public int hashCode() {
//        int hash = 7;
//        hash = 97 * hash + this.id;
//        hash = 97 * hash + Objects.hashCode(this.username);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        final HangmanRoundPK other = (HangmanRoundPK) obj;
//        if (this.id != other.id) {
//            return false;
//        }
//        if (!Objects.equals(this.username, other.username)) {
//            return false;
//        }
//        return true;
//    }
//    
//    
//}
