package com.asr.processor;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.asr.model.Player;

public class TopFivePlayersProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

        Map<String, Object> inputJson = exchange.getIn().getBody(Map.class);

        List<Map<String, Object>> players = (List<Map<String, Object>>) inputJson.get("players");

        List<Player> topFivePlayers = players.stream().map(p -> {
            Player player = new Player();
            player.setName((String) p.get("name"));
            player.setRuns((Integer) p.get("runs"));
            return player;
        }).sorted(Comparator.comparingInt(Player::getRuns).reversed()).limit(5).collect(Collectors.toList());

        exchange.getIn().setBody(topFivePlayers);

    }

}
