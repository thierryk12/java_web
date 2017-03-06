/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supfitness.dao;

import com.supinfo.supfitness.entity.*;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Andrey
 */

@Local
public interface TrackDao {
    
    public Track addTrack(Track track);
    
    public List<Track> retrieveAllTracks();
    
    public Track retrieveTrackById(Long id);
    
    public List<Track> retrieveTrackByUser(User user);
    
    public Track updateTrack(Track track);
    
    public void removeTrack(Track track);
    
    public List<Track> getTrackCountByUser(User user);
    
}
