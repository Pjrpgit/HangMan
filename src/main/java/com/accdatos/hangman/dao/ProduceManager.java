/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accdatos.hangman.dao;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author soad_
 */
public class ProduceManager {

    private static EntityManagerFactory emf;
    private static ProduceManager pm;

  private ProduceManager() {  
      
      emf=Persistence.createEntityManagerFactory("com.accdatos_Hangman_jar_1.0-SNAPSHOTPU");
  }

  public static ProduceManager getInstance() {
        if (pm == null || !emf.isOpen()) {
            pm = new ProduceManager();
        }
        return pm;
    }

    public static void close() {
        if (emf.isOpen()) {
            emf.close();
        }
    }

    public static EntityManagerFactory getEmf() {
        return emf;
    }

    public EntityManager getManager() {
        getInstance();
        return emf.createEntityManager();
    }

}

