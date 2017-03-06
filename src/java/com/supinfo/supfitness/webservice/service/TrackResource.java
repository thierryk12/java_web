/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supfitness.webservice.service;

import com.supinfo.supfitness.dao.TrackDao;
import com.supinfo.supfitness.dao.UserDao;
import com.supinfo.supfitness.entity.Track;
import com.supinfo.supfitness.entity.User;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.jws.WebParam;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.Context;
import java.security.Principal;

/**
 * REST Web Service
 *
 * @author Andrey
 */
@Path("track")
public class TrackResource {

    @Context
    private UriInfo context;
    
     @EJB
    private TrackDao trackDao;
     
     @EJB
    private UserDao userDao;

    /**
     * Creates a new instance of GenericResource
     */
    public TrackResource() {
    }

    /**
     * Retrieves representation of an instance of com.supinfo.supfitness.webservice.service.TrackResource
     * @return an instance of com.supinfo.supfitness.entity.Track
     */
    @GET
    
    @Produces(MediaType.APPLICATION_JSON)
    public List<Track> getJson() {
        //TODO return proper representation object
       
        return trackDao.retrieveAllTracks();
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Track getTrack(@PathParam("id") String id) {
        //TODO return proper representation object
       
        return trackDao.retrieveTrackById(Long.parseLong(id));
    }
    
    @Path("/create")
    @PUT
    public void create(@QueryParam("speed") String title,
                       @QueryParam("latitude") String latitude,
                       @QueryParam("longitude") String longitude) {
        
       
        User u = userDao.retrieveUserById(Long.parseLong("1"));
        Track t = new Track();
        t.setSpeed(Long.parseLong("100"));
        t.setLatitude(Double.parseDouble(latitude));
        t.setLongitude(Double.parseDouble(longitude));
        t.setUser(u);
        Date now = new Date(); 
        t.setDate(now);
        
        Date instant = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat( "HH:mm" );
        String time = sdf.format(instant);
        t.setTime(time);
        
        
        
        
        trackDao.addTrack(t);
    }
    
    
    

    /**
     * PUT method for updating or creating an instance of TrackResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(Track content) {
    }
    
    @GET
    @Path("/test")
    @Produces("text/plain")
    public String hello(@Context SecurityContext sc) {
        String user = "";
        if (sc != null) {
            String p  = sc.getAuthenticationScheme();
            return "Hello " + p + "!";
        }
        else
            return "nonononjo!";
        
    }
    
    
}
