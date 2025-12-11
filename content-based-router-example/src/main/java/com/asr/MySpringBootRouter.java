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
        from("file:input-json?noop=true")
                .unmarshal().json()
                .split().jsonpath("$.orders[*]") // split each record
                .choice()
                .when().simple("${body[category]} == 'electronics'")
                .marshal().json()
                .to("file:output/electronics?fileName=order-${body[orderId]}.json")

                .when().simple("${body[category]} == 'grocery'")
                .marshal().json()
                .to("file:output/grocery?fileName=order-${body[orderId]}.json")

                .when().simple("${body[category]} == 'fashion'")
                .marshal().json()
                .to("file:output/fashion?fileName=order-${body[orderId]}.json")

                .otherwise()
                .marshal().json()
                .to("file:output/others?fileName=order-${body[orderId]}.json")
                .end();

    }

}
