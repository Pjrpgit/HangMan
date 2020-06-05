/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accdatos.hangman.dao;

import java.util.List;

/**
 *
 * @author soad_
 */
public interface DAO<T,K> {
    
    List<K> getAll();
    K get(T id);
    void delete(T id);
    void create(K object);
    void update(K object);
}
