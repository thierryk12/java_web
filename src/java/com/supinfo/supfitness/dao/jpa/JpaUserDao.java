/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supfitness.dao.jpa;

import com.supinfo.supfitness.dao.UserDao;
import com.supinfo.supfitness.entity.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Andrey
 */

@Stateless
public class JpaUserDao implements UserDao{
    
    @PersistenceContext(unitName = "SUPFitnessPU")
    private EntityManager entityManager;
    
    @Override
    public User addUser(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public List<User> retrieveAllUsers() {
        Query query = entityManager.createQuery("SELECT u from User as u");
        return query.getResultList();
    }

    @Override
    public User retrieveUserById(Long id) {
        return(User) entityManager.find(User.class, id);
    }

    @Override
    public User updateUser(User user) {
        return entityManager.merge(user);
    }

    @Override
    public void removeUser(User user) {
       User u = entityManager.getReference(User.class, user.getId());
        entityManager.remove(u);
    }
    
}
