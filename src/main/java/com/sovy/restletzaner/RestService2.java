/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sovy.restletzaner;

import java.sql.SQLException;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

/**
 *
 * @author stefan.tomasik
 */
public class RestService2 extends ServerResource {

    public Database Databaza = new Database();

    @Get("/find/")
     @Consumes(MediaType.APPLICATION_JSON)
//    @Path("/find/{id}/")
 //   @Produces(MediaType.APPLICATION_JSON)
    public Zaner findData() throws SQLException {
       Integer id;
       Integer index= (Integer) this.getRequestAttributes().get("id");
     
        return Databaza.getZanerById(index);
    }
}
