package com.asr;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * A simple Camel route that triggers from a timer and calls a bean and prints
 * to system out.
 * <p/>
 * Use <tt>@Component</tt> to make Camel auto detect this route when starting.
 */
@Component
public class MySpringBootRouter extends RouteBuilder {

    @Override
    public void configure() {
        from("timer://fetchOrders?period=5000") // triggers every 5 secs
                .log("Calling Spring Boot API...")
                .to("http://localhost:8080/api/food/orders") // calling API
                .log("Response from Spring: ${body}");
    }

}
