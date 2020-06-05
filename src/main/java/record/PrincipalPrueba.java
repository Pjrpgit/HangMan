/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package record;

import record.Prueba;
import com.accdatos.hangman.model.User;
import dao.ProduceManager;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author soad_
 */
public class PrincipalPrueba {
    public static void main(String[] args){
        //EntityManager em=ProduceManager.getInstance().getManager();
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("mio");
        EntityManager em=emf.createEntityManager();
        Prueba p=new Prueba();
        p.setCampo("uf");
        p.setFunciona(1);
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
        em.close();
    }
}
