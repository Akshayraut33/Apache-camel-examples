package com.asr;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:start")
                .aggregate(header("fid"), new MyAggregationStrategy())
                .completionSize(3)
                .to("mock:result");
    }

}
