/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sovy.restletzaner;

import com.google.gson.Gson;
import java.sql.SQLException;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

/**
 *
 * @author stefan.tomasik
 */
public class RestService2 extends ServerResource {

    public Database Databaza = new Database();

    @Get("/find/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Zaner findData() throws SQLException {
        Integer id = Integer.valueOf(this.getAttribute("id"));
        return Databaza.getZanerById(id);
    }

    @Put("/update/{id}")
    public void updateData(String data) throws SQLException {
      Integer ud = Integer.valueOf(this.getAttribute("id"));
       Gson gson = new Gson();
       Databaza.updateById(ud,gson.fromJson( data, Zaner.class));
    }
}
