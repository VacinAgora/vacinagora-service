package com.vacinagora.vacinagora_server.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vacinagora.vacinagora_server.models.Place;
import com.vacinagora.vacinagora_server.models.PlaceDto;
import com.vacinagora.vacinagora_server.models.Position;
import com.vacinagora.vacinagora_server.service.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/kafka/publish")
public class KafkaController {

    private final Producer producer;

    @Autowired
    KafkaController(final Producer producer) {
        this.producer = producer;
    }

    @PostMapping(value = "/positions")
    public void sendMessageToPositionKafkaTopic(@RequestBody Position position) {
        this.producer.sendPosition(position);
    }

    @PostMapping(value = "/places")
    public void sendMessageToGeofenceKafkaTopic(@RequestBody PlaceDto placeDto) {
        ObjectMapper objectMapper = new ObjectMapper();
        String geometryJson = "";
        try {
            geometryJson = objectMapper.writeValueAsString(placeDto.getGeometry());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Place place = new Place(placeDto.getPlaceId(), geometryJson);
        this.producer.sendGeofence(place);
    }

}
