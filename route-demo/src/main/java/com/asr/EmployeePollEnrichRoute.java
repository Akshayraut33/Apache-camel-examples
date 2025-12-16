package com.asr;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class EmployeePollEnrichRoute extends RouteBuilder {

    @Override
    public void configure() {

        from("platform-http:/getAllEmployees?httpMethodRestrict=GET")
                .routeId("get-all-employees-pollenrich")
                .log("Request received for employees")

                // Fetch file on demand
                .pollEnrich("file:data?fileName=employees.json&noop=true", 5000)

                .convertBodyTo(String.class)
                .setHeader("Content-Type", constant("application/json"))
                .setHeader("CamelHttpResponseCode", constant(200))

                .log("Employees file fetched using pollEnrich");
    }
}
