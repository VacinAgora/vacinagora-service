package com.vacinagora.vacinagora_server.controller;

import com.vacinagora.vacinagora_server.models.Place;
import com.vacinagora.vacinagora_server.models.Position;
import com.vacinagora.vacinagora_server.models.PositionEvent;
import com.vacinagora.vacinagora_server.service.Producer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kafka/publish")
public class KafkaController {

    private final Producer producer;

    KafkaController(Producer producer) {
        this.producer = producer;
    }

    @PostMapping(value = "/positions")
    public void sendMessageToPositionKafkaTopic(@RequestBody Position position) {
        this.producer.sendPosition(position);
    }

    @PostMapping(value = "/places")
    public void sendMessageToGeofenceKafkaTopic(@RequestBody Place place) {
        this.producer.sendPlace(place);
    }

    @PostMapping(value = "/events")
    public void sendMessageToWarehouseKafkaTopic(@RequestBody PositionEvent event) {
        this.producer.sendPositionEvent(event);
    }

}
