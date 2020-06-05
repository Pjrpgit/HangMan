/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accdatos.hangman.dao;

import com.accdatos.hangman.model.Guess;
import com.accdatos.hangman.model.HangmanRound;
import com.accdatos.hangman.model.User;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author soad_
 */
public class UserDAO implements DAO<String, User> {

    EntityManager em;

    @Override
    public List<User> getAll() {
        em = ProduceManager.getInstance().getManager();
        List<User> users = em.createNamedQuery("User.findAll").getResultList();
        em.close();
        return users;
    }

    @Override
    public User get(String id) {
        em = ProduceManager.getInstance().getManager();
        User u = em.find(User.class, id);
        em.close();
        return u;
    }

    @Override
    public void delete(String id) {
        em = ProduceManager.getInstance().getManager();
        em.getTransaction().begin();
        em.remove(id);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void create(User user) {
        em = ProduceManager.getInstance().getManager();
        em.getTransaction().begin();
        em.persist(user);
        em.flush();
        em.getTransaction().commit();
        em.close();
    }

    public void createHangmanRound(HangmanRound hr) {
        em = ProduceManager.getInstance().getManager();
        em.getTransaction().begin();
        em.persist(hr);
        em.flush();
        em.getTransaction().commit();
        em.close();
    }

    public void createGuess(Guess guess) {
        em = ProduceManager.getInstance().getManager();
        em.getTransaction().begin();
        em.persist(guess);
        em.flush();
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void update(User user) {
        em = ProduceManager.getInstance().getManager();
        em.getTransaction().begin();
        User s = em.find(User.class, user.getUsername());
        if (s != null) {
            System.out.println("guardando las siguientes:");
            System.out.println(user.getHangmanRounds());
            s.setHangmanRounds(user.getHangmanRounds());
            //em.merge(s);
            em.getTransaction().commit();
            //em.flush();
            em.close();
            ProduceManager.close();
        } else {
            em.persist(user);
            em.getTransaction().commit();
            em.close();
            ProduceManager.close();
        }
    }

    public void aux(HangmanRound rm) {
        em = ProduceManager.getInstance().getManager();
        em.getTransaction().begin();
        if (em.find(HangmanRound.class, rm.getId()) != null) {
            HangmanRound hr = em.find(HangmanRound.class, rm.getId());
            auxS(rm.getGuesses());
            hr.setFinished(rm.getFinished());
            hr.setUsername(rm.getUsername());
            hr.setGuesses(rm.getGuesses());
            hr.setHiddenWord(rm.getHiddenWord());
            hr.setMaxNumErrors(rm.getMaxNumErrors());
            hr.setNumErrors(rm.getNumErrors());
            hr.setUncoveredWord(rm.getUncoveredWord());
            em.getTransaction().commit();
        } else {
            em.persist(rm);
            em.flush();
            em.getTransaction().commit();
        }
        em.close();
        ProduceManager.close();
    }

    public void auxS(List<Guess> guesses) {
        em = ProduceManager.getInstance().getManager();
        em.getTransaction().begin();
        for (Guess g : guesses) {
            Guess aux = em.find(Guess.class, g.getId());
            if (aux != null) {
                aux.setCurrentUncoveredWord(g.getCurrentUncoveredWord());
                aux.setLetter(g.getLetter());
                aux.setRoundid(g.getRoundid());
                em.getTransaction().commit();
            } else {
                em.persist(g);
                em.getTransaction().commit();
            }
        }
        em.close();
        ProduceManager.close();
    }

    public void save(User user) {
        em = ProduceManager.getInstance().getManager();
        //User saved = em.find(User.class, user.getUsername());
        for (HangmanRound rm : user.getHangmanRounds()) {
            HangmanRound hr = em.find(HangmanRound.class, rm.getId());
            em.getTransaction().begin();
            hr.setFinished(rm.getFinished());
            hr.setUsername(rm.getUsername());
            hr.setGuesses(rm.getGuesses());
            hr.setHiddenWord(rm.getHiddenWord());
            hr.setMaxNumErrors(rm.getMaxNumErrors());
            hr.setNumErrors(rm.getNumErrors());
            hr.setUncoveredWord(rm.getUncoveredWord());
            em.flush();
            em.getTransaction().commit();
        }
        em.close();
    }
    
        public void save(HangmanRound round) {          
        em = ProduceManager.getInstance().getManager();
        HangmanRound saved = em.find(HangmanRound.class, round.getId());
        for (Guess aux : saved.getGuesses()) {
            Guess g = em.find(Guess.class, aux.getId());
            em.getTransaction().begin();
            g.setCurrentUncoveredWord(aux.getCurrentUncoveredWord());
            em.flush();
            em.getTransaction().commit();
        }
        em.close();
    }
    public void mergeUser(User user){
        em = ProduceManager.getInstance().getManager();
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
        em.close();
    }
    public void mergeHangmanRound(HangmanRound hr){
        em = ProduceManager.getInstance().getManager();
        em.getTransaction().begin();
        em.merge(hr);
        em.getTransaction().commit();
        em.close();
    }
    public void mergeGuess(Guess guess){
        em = ProduceManager.getInstance().getManager();
        em.getTransaction().begin();
        em.merge(guess);
        em.getTransaction().commit();
        em.close();
        
    }

//    private void actuUser() {
//        em.ProduceManager.getInstance().getManager();
//        
//    }


}
