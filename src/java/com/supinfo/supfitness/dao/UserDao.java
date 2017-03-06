/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supfitness.dao;

import com.supinfo.supfitness.entity.User;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Andrey
 */

@Local
public interface UserDao {
    
    public User addUser(User user);
    
    public List<User> retrieveAllUsers();
    
    public User retrieveUserById(Long id);
    
    public User updateUser(User user);
    
    public void removeUser(User user);
    
}
