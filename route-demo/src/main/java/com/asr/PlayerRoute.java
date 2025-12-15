package com.asr;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class PlayerRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        // 1. Configure the REST endpoint to use the platform-http component
        // (which uses the embedded Spring Boot server)
        restConfiguration()
                .component("platform-http")
                .port(8080)
                .bindingMode(org.apache.camel.model.rest.RestBindingMode.json);

        //

        // 2. Define the REST service
        rest("/api/v1")
                .get("/players/names")
                .produces("application/json") // Define the response content type
                .route()
                // A. Read the JSON file content into the message body
                .to("file:src/main/resources/data/?fileName=players.json&noop=true")
                // `noop=true` prevents the file from being moved/deleted after reading

                // B. Use JSONPath to extract only the 'name' field from the 'players' array
                // $..name is a recursive descent operator that selects the value of all 'name'
                // fields
                .transform().jsonpath("$..name")

                // C. Log the result
                .log("Successfully extracted player names: ${body}")

                // D. The final body (a List of Strings) is automatically marshalled to a JSON
                // array
                // thanks to the 'produces("application/json")' and
                // 'bindingMode(RestBindingMode.json)'
                .endRest();
    }
}
