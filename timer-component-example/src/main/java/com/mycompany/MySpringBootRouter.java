package com.mycompany;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * A simple Camel route that triggers from a timer and calls a bean and prints
 * to system out.
 * <p/>
 * Use <tt>@Component</tt> to make Camel auto detect this route when starting.
 */
@Component
public class MySpringBootRouter extends BaseRouteBuilder {

    @Override
    public void configure() {

        // use-case1
        // logInfo("Start.....");
        // from("timer:my-timer?period=1000")
        // .routeId("timer-demo-route")
        // .transform().constant("The constant msg is here !")
        // .log("${body}")
        // .to("log:my-log");
        // logInfo("end....");

        // use-case2
        // // Runs every 2 seconds and prints the current timestamp
        // from("timer:simple-timer?period=2000")
        // .log("Triggered at: ${date:now}");

        // Executes custom processor logic once per second
        from("timer:processor-timer?period=1000")
                .process(exchange -> {
                    exchange.getMessage().setBody("Processing at: " + System.currentTimeMillis());
                })
                .log("${body}");

    }

}
