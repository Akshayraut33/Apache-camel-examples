package com.asr;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;

public class MyAggregationStrategy implements AggregationStrategy {

    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        if (oldExchange == null) {
            return newExchange;
        }

        String oldData = oldExchange.getIn().getBody(String.class);
        String newData = newExchange.getIn().getBody(String.class);

        oldExchange.getIn().setBody(oldData+ "," +newData);

        return oldExchange;
    }
}
