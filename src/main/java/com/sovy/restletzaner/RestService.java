/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sovy.restletzaner;

import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
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
public class RestService extends ServerResource {

    public Database Databaza = new Database();

    @Get("/all")
//    @Path("/data/")
   @Produces(MediaType.APPLICATION_JSON)
    public List<Zaner> getData() throws SQLException {
        return Databaza.readData();
    }

    @Post("/add/")
//    @Path("/add/")
  @Consumes(MediaType.APPLICATION_JSON)
    public void insertData(String org) throws SQLException {
        Gson gson = new Gson();
        Databaza.insertData(gson.fromJson(org, Zaner.class));
    }

    @Delete("/del/")
//    @Path("/del/")
    @Consumes(MediaType.APPLICATION_JSON)
    public void removeData(String org) throws SQLException {
        Gson gson = new Gson();
        Databaza.remove(gson.fromJson(org, Zaner.class));
    }

}
