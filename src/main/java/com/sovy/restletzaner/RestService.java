/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sovy.restletzaner;

import com.google.gson.Gson;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.restlet.resource.Delete;
import org.restlet.resource.Post;

/**
 *
 * @author stefan.tomasik
 */
public class RestService extends ServerResource implements MyOperations {

    public Database Databaza = new Database();

    @Get("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Zaner> getData() throws SQLException, IOException {
        return Databaza.readData();
    }

    @Post("/add/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public void insertData(String org) throws SQLException, MyValidationException {
        Gson gson = new Gson();
        validateName(org);
        Zaner zaner = gson.fromJson(org, Zaner.class);
        try {
            Databaza.insertData(zaner);
        } catch (IOException ex) {
            Logger.getLogger(RestService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RestService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param org
     * @throws SQLException
     * @throws MyValidationException
     */
    @Delete("/del/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public void removeData(String org) throws SQLException, MyValidationException {
        try {
            Gson gson = new Gson();
            validateId(org);
            Zaner zaner = gson.fromJson(org, Zaner.class);
            Databaza.remove(zaner);
        } catch (IOException ex) {
            Logger.getLogger(RestService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RestService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void validateId(String org)  {
        Exception Exception = new Exception();
        if ((org == null) || (org.length() <= 3) ||(!org.substring(3, 5).equals("id"))) {
            throw new MyValidationException("error", Exception);
        }
    }
    
    
      public void validateName(String org)  {
        Exception Exception = new Exception();
        if ((org == null) || (org.length() <= 3) ||(!org.substring(3, 7).equals("meno"))) {
            throw new MyValidationException("error", Exception);
        }
    }
    

}
