package com.asr.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class PlayerRoute extends RouteBuilder {

    @Override
    public void configure() {

        from("file:input?noop=true")
                .routeId("player-cbr-route")
                .log("Received file: ${file:name}")

                // Split players array
                .log("Before split (full JSON) :${body}")
                .split().jsonpath("$.players[*]")
                .log("After split ${body}")
                .log("Processing player: ${body}")

                // Convert Map -> JSON String
                .marshal().json()

                // Content Based Routing
                .choice()
                .when(simple("${bodyAs(String)} contains '\"team\":\"MI\"'"))
                .to("file:output/mi?fileName=${file:name.noext}-mi.json")
                .when(simple("${bodyAs(String)} contains '\"team\":\"CSK\"'"))
                .to("file:output/csk?fileName=${file:name.noext}-csk.json")
                .otherwise()
                .to("file:output/others?fileName=${file:name.noext}-others.json")
                .end()
                .end();
    }
}
