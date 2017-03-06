/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supfitness.controller;

import com.supinfo.supfitness.dao.TrackDao;
import com.supinfo.supfitness.dao.UserDao;
import com.supinfo.supfitness.entity.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.Date;
import javax.faces.context.FacesContext;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.servlet.http.HttpSession;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author Andrey
 */

@ManagedBean
@RequestScoped
@Stateless
public class TrackController {
    
    @EJB
    private UserDao userDao;
    
    @EJB
    private TrackDao trackDao;
    
    private double longitude;
    
    private double latitude;
    
    private double speed;
    
    private Date date;
    
    private Date time;
    
    private User user;
    
    
    
    
    private List<Track> tracks;
    
    private Track track;

    private DataModel trackModel = new ListDataModel();
    
    public List<Track> getTracks() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        User u = (User) session.getAttribute("user");
        tracks = trackDao.retrieveTrackByUser(u);
        return tracks;
    }
    
    public int getTrackCount() {

        return trackDao.retrieveAllTracks().size();
    }
    
    
    
    public int getTrackCountByUser()
    {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        User u = (User) session.getAttribute("user");
        
        return trackDao.getTrackCountByUser(u).size();
    }
    
    public String addcurrenttrack()
    {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        User u = (User) session.getAttribute("user");
        Track t = new Track();
        t.setSpeed(Long.parseLong("100"));
        t.setLatitude(latitude);
        t.setLongitude(longitude);
        t.setUser(u);
        Date now = new Date(); 
        t.setDate(now);
        
        Date instant = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat( "HH:mm" );
        String time = sdf.format(instant);
        t.setTime(time);
        
        
        
        
        trackDao.addTrack(t);
        
        
        
        return "correct";
    
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

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    public DataModel getTrackModel() {
        trackModel.setWrappedData(getTracks());
        return trackModel;
    }

    public void setTrackModel(DataModel trackModel) {
        this.trackModel = trackModel;
    }

    
    
    
    
    
    
    
    
}
