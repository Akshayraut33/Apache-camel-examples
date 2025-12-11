package com.mycompany;

import org.apache.camel.builder.RouteBuilder;

public abstract class BaseRouteBuilder extends RouteBuilder {

    public void logInfo(String log) {
        System.out.println("LOG :- " + log);
    }

}
