/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sovy.restletzaner;

import java.sql.SQLException;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import org.restlet.resource.Delete;

/**
 *
 * @author stefan.tomasik
 */
public interface MyOperations {
    @Delete("/del/")
    @Consumes(MediaType.APPLICATION_JSON)
    public void removeData(String org) throws SQLException,MyValidationException;
}

