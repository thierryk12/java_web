/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supfitness.dao.jpa;

import com.supinfo.supfitness.dao.TrackDao;
import com.supinfo.supfitness.entity.Track;
import com.supinfo.supfitness.entity.User;
import java.util.ArrayList;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import javax.persistence.criteria.Root;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;





/**
 *
 * @author Andrey
 */


@Stateless
public class JpaTrackDao implements TrackDao{
    
    @PersistenceContext(unitName = "SUPFitnessPU")
    private EntityManager entityManager;
    
    static List<Long> l1;

    @Override
    public Track addTrack(Track track) {
        entityManager.persist(track);
        return track;
    }

    @Override
    public List<Track> retrieveAllTracks() {
        
        return entityManager.createQuery("SELECT c from Track as c").getResultList();
    }

    @Override
    public Track retrieveTrackById(Long id) {
        return entityManager.find(Track.class, id);
        
    }

    @Override
    public List<Track> retrieveTrackByUser(User user) {
        
         List<Track> track = new ArrayList<Track>();
        List<Object> idlist = new ArrayList<Object>();
        
         idlist = entityManager.createQuery("SELECT max(c.id) as id,max(c.time) from Track as c where c.user.id =:userid order by c.date ").setParameter("userid",user.getId()).getResultList();
         
         Gson gson = new Gson();
         String jsonCartList = gson.toJson(idlist);
         
         
         JsonElement json = new JsonParser().parse(jsonCartList);
 
         JsonArray array= json.getAsJsonArray();
         
         
         for (int i = 0; i < array.size(); i++) {
             
             String i1 = array.get(i).toString();
              int commalocation = i1.indexOf(',');
              Long id = Long.parseLong(i1.substring(1, commalocation));
              Track t = (Track)entityManager.createQuery("SELECT t from Track as t where t.id = :id").setParameter("id",id).getResultList().get(0);
              
              track.add(t);
                  
             }
         
         
        
        
         return track;
        
    }

    @Override
    public Track updateTrack(Track track) {
        return entityManager.merge(track);
    }

    @Override
    public void removeTrack(Track track) {
        Track c = entityManager.getReference(Track.class, track.getId());
        entityManager.remove(c);
    }

    @Override
    public List<Track> getTrackCountByUser(User user) {
        List<Track> tracks = entityManager.createQuery("SELECT t from Track as t where t.user.id =:userid ").setParameter("userid",user.getId()).getResultList();
       return tracks;
    }
    
}
