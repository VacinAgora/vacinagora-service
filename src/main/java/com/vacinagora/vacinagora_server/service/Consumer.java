package com.vacinagora.vacinagora_server.service;

import com.vacinagora.vacinagora_server.models.Place;
import com.vacinagora.vacinagora_server.tile38.Tile38Service;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    private final Tile38Service tile38Service;

    public Consumer(Tile38Service tile38Service) {
        this.tile38Service = tile38Service;
    }

//    @KafkaListener(topics = "places", groupId = "group_id")
//    public void consume(Place place) {
//        tile38Service.createPositionHook(place);
//    }

}