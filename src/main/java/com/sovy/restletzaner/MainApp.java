/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sovy.restletzaner;

import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.routing.Router;

/**
 *
 * @author stefan.tomasik
 */
public class MainApp extends Application {

    static final int port = 8182;
    static final Router router = new Router();
    static Context ctx;

    public static void main(String[] args) throws Exception {

        org.restlet.Application myApp = new org.restlet.Application() {

            @Override
            public org.restlet.Restlet createInboundRoot() {

                ctx.getParameters().add("port", Integer.toString(port));
                router.setContext(ctx);
                return router;
            }
        ;
        };
    Component component = new Component();

        component.getDefaultHost().attach("/all/", RestService.class);
        component.getDefaultHost().attach("/add/", RestService.class);
        component.getDefaultHost().attach("/del/", RestService.class);
        component.getDefaultHost().attach("/update/{id}", RestService2.class);
        component.getDefaultHost().attach("/find/{id}", RestService2.class);
        new Server(Protocol.HTTP, port, component).start();

    }
}
