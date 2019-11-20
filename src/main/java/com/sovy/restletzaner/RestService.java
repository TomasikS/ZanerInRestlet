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
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.restlet.resource.Delete;
import org.restlet.resource.Post;
import org.restlet.data.Status;

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
    public Status insertData(String org) throws SQLException {
        Gson gson = new Gson();
        Status status = null;

        if ((org.length() == 0) || (org.length() == 3)) {
            System.out.println("error 200");
            status = Status.SERVER_ERROR_SERVICE_UNAVAILABLE;
        } else if ((org.substring(3, 7).equals("meno")) && (org.length() > 6)) {
            Zaner zaner = gson.fromJson(org, Zaner.class);
            Databaza.insertData(zaner);
            status = Status.SUCCESS_OK;

        } else {
            status = Status.SERVER_ERROR_INTERNAL;
            System.out.println("error 500");
        }

        return status;
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
        Gson gson = new Gson();
        validate(org);
        Zaner zaner = gson.fromJson(org, Zaner.class);
        Databaza.remove(zaner);
    }

    public void validate(String org)  {
        Exception Exception = new Exception();
        System.out.print(org.substring(3, 5));
        if ((org == null) || (org.length() <= 3) ||(!org.substring(3, 5).equals("id"))) {
            throw new MyValidationException("error", Exception);
        }
    }

}
