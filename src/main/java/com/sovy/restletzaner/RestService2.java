/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sovy.restletzaner;

import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.io.IOException;
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
    public Zaner findData() throws SQLException, MyValidationException, IOException, FileNotFoundException, ClassNotFoundException {
        Integer id = Integer.valueOf(this.getAttribute("id"));
        validateId(id);
        return Databaza.getZanerById(id);
    }

    @Put("/update/{id}")
    public void updateData(String data) throws SQLException, IOException, ClassNotFoundException, MyValidationException {
        Integer id = Integer.valueOf(this.getAttribute("id"));
        validateId(id);
        validateName(data);
        Gson gson = new Gson();
        Databaza.updateById(id, gson.fromJson(data, Zaner.class));

    }

    public void validateId(Integer id) {
        Exception Exception = new Exception();
        if (id == null) {
            throw new MyValidationException("error", Exception);
        }
    }

    public void validateName(String org) {
        Exception Exception = new Exception();
        if ((org == null) || (org.length() <= 3) || (!org.substring(3, 7).equals("meno"))) {
            throw new MyValidationException("error", Exception);
        }
    }
}
