package com.vacinagora.vacinagora_server.service;

import com.vacinagora.vacinagora_server.models.Place;
import com.vacinagora.vacinagora_server.models.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

//    private static final String TOPIC = "quotes";
    private static final String TOPIC_POSITIONS = "positions";
    private static final String TOPIC_PLACES = "places";

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendPosition(Position position) {
        logger.info(String.format("#### -> Producing message -> %s", position.toString()));
        this.kafkaTemplate.send(TOPIC_POSITIONS, position);
    }

    public void sendGeofence(Place place) {
        logger.info(String.format("#### -> Producing message -> %s", place.toString()));
        this.kafkaTemplate.send(TOPIC_PLACES, place);
    }

}
