package com.asr.route;

import com.asr.processor.TopFivePlayersProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class PlayerRoute extends RouteBuilder {

    @Override
    public void configure() {

        from("file:input?noop=true")
                .routeId("player-sort-route")

                .log("Received file: ${file:name}")
                .log("Raw body type: ${body.getClass}")

                // ✅ Convert JSON String → Map
                .unmarshal().json(JsonLibrary.Jackson)

                .log("After unmarshal body type is --: ${body.getClass}")
                .log("Unmarshalled JSON: ${body}")

                // ✅ Now processor will work
                .process(new TopFivePlayersProcessor())

                .marshal().json(true)

                .to("file:output?fileName=top-five-players.json");
    }
}
