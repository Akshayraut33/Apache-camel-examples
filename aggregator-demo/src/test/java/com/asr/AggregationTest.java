package com.asr;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.jupiter.api.Test;

// to test the application 
public class AggregationTest extends CamelTestSupport {

    @Override
    protected RouteBuilder createRouteBuilder() {
        return new MyRouter();
    }

    @Test
    public void testAggregation() throws Exception {

        MockEndpoint mock = getMockEndpoint("mock:result");
        mock.expectedMessageCount(1);
        mock.expectedBodiesReceived("A,B,C");

        ProducerTemplate template = context.createProducerTemplate();

        template.sendBodyAndHeader("direct:start", "A", "fid", "1");
        template.sendBodyAndHeader("direct:start", "B", "fid", "1");
        template.sendBodyAndHeader("direct:start", "C", "fid", "1");

        mock.assertIsSatisfied();
    }

}
