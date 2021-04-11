package com.vacinagora.vacinagora_server.service;

import com.vacinagora.vacinagora_server.models.Place;
import com.vacinagora.vacinagora_server.models.Position;
import com.vacinagora.vacinagora_server.models.PositionEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    private static final String TOPIC_POSITIONS = "positions";
    private static final String TOPIC_PLACES = "places";
    private static final String TOPIC_POSITION_EVENTS = "position_events";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public Producer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendPosition(Position position) {
        this.kafkaTemplate.send(TOPIC_POSITIONS, position);
    }

    public void sendPlace(Place place) {
        this.kafkaTemplate.send(TOPIC_PLACES, place);
    }

    public void sendPositionEvent(PositionEvent event) {
        this.kafkaTemplate.send(TOPIC_POSITION_EVENTS, event);
    }

}
