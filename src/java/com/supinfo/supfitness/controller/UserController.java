/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supfitness.controller;

import com.supinfo.supfitness.dao.TrackDao;
import com.supinfo.supfitness.dao.UserDao;
import com.supinfo.supfitness.entity.User;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.Date;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


/**
 *
 * @author Andrey
 */
@ManagedBean
@RequestScoped
@Stateless
public class UserController {
    
    @EJB
    private UserDao userDao;
    
    @EJB
    private TrackDao trackDao;
    
    private String username;
    
    private String password;
    
    private String firstName;
    
    private String lastName;
    
    private Integer postalcode;
    
    private String email;
    
    public List<User> getAllUsers() {
        return userDao.retrieveAllUsers();
    }
    
    
    
    public int getUserCount() 
    {
        
        return userDao.retrieveAllUsers().size();
        }
    
    public String register() throws ParseException{
        
       
    User user = new User();
    user.setUsername(username);
    user.setPassword(password);
    user.setFirstName(firstName);
    user.setLastName(lastName);
    user.setEmail(email);
    user.setPostalcode(postalcode);
    Date now = new Date(); 
            user.setBirth(now);
    userDao.addUser(user);
    HttpSession session =(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                session.setAttribute("user", user);
                session.setAttribute("userid", user.getId());
    return "correct";
    
    }
    
    public String login(){
        List<User> users = userDao.retrieveAllUsers();
        for (User user : users) {
            if(user.getUsername().equals(this.username) && user.getPassword().equals(this.password)){
                HttpSession session =(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                session.setAttribute("user", user);
                
                    return "correct";
            }
        }
        return "incorrect";
    }
    
    public String profile(){
        HttpSession session =(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
             User session_user = (User)session.getAttribute("user");
             this.setPassword(session_user.getPassword());
             this.setFirstName(session_user.getFirstName());
             this.setLastName(session_user.getLastName());
             this.setEmail(session_user.getEmail());
             this.setPostalcode(session_user.getPostalcode());
             
        
        return "profile";}
    
    
    public String updateProfile(){
        HttpSession session =(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
             User session_user = (User)session.getAttribute("user");
             session_user.setPassword(password);
             session_user.setFirstName(firstName);
             session_user.setLastName(lastName);
             session_user.setEmail(email);
             userDao.updateUser(session_user);
    return "correct";}
    
    
    public String logout() throws IOException
    {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index?faces-redirect=true";
        }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public TrackDao getTrackDao() {
        return trackDao;
    }

    public void setTrackDao(TrackDao trackDao) {
        this.trackDao = trackDao;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(Integer postalcode) {
        this.postalcode = postalcode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
    
    
}
