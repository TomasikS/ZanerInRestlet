/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sovy.restletzaner;

import org.restlet.resource.Status;

/**
 *
 * @author stefan.tomasik
 */
@Status(value = 400, serialize = true)
public class MyValidationException extends RuntimeException {
    public MyValidationException(String message, Exception e) {
        super(message, e);
    }
   } 

