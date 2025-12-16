package com.asr;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class PlayerRoute extends RouteBuilder {

    // 1. Define a unique endpoint URI for the REST service to route to
    private static final String PROCESS_PLAYERS_URI = "direct:process-players-file";

    @Override
    public void configure() throws Exception {

        // --- PART A: REST DEFINITION (Only defines the HTTP endpoint) ---
        restConfiguration()
                .component("platform-http")
                .port(8080)
                .bindingMode(org.apache.camel.model.rest.RestBindingMode.json);

        rest("/api/v1")
                .get("/players/names")
                .produces("application/json")
                // CRITICAL FIX: Route the REST call directly to a 'direct:' endpoint
                .to(PROCESS_PLAYERS_URI);

        // --- PART B: ROUTE LOGIC (The actual work starts here) ---
        // Start a completely separate route from the 'direct' endpoint defined above
        from(PROCESS_PLAYERS_URI)
                .routeId("PlayersFileReader") // It's good practice to name your routes

                // 1. Read the JSON file content into the message body
                .to("file:src/main/resources/data/?fileName=players.json&noop=true")

                // 2. Use JSONPath to extract only the 'name' field from the 'players' array
                .transform().jsonpath("$..name")

                // 3. Log the result
                .log("Successfully extracted player names: ${body}");
        // The final body (a List of Strings) is returned as the HTTP response
    }
}