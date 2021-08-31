package com.vacinagora.vacinagora_server.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vacinagora.vacinagora_server.models.MapObject;
import com.vacinagora.vacinagora_server.models.Place;
import com.vacinagora.vacinagora_server.tile38.Tile38Service;
import org.springframework.web.bind.annotation.*;

import java.io.UncheckedIOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/places")
public class PlacesController {

    private final Tile38Service tile38Service;

    public PlacesController(Tile38Service tile38Service) {
        this.tile38Service = tile38Service;
    }

    private Place getPlace(List<Object> placeList) {
        try {
            String placeId = (String) placeList.get(0);
            String placeString = (String) placeList.get(1);

            MapObject mapObject = new ObjectMapper().readValue(placeString, MapObject.class);

            List<Double> coordinates = mapObject.getCoordinates();
            Double longitude = coordinates.get(0);
            Double latitude = coordinates.get(1);

            return new Place(placeId, latitude, longitude, "");
        } catch (JsonProcessingException e) {
            throw new UncheckedIOException(e);
        }
    }

    @GetMapping
    public List<Place> listPlacesOnArea(Double swLat, Double swLng, Double neLat, Double neLng) {
        List<Object> objects = tile38Service.listPlacesWithinBounds(swLat, swLng, neLat, neLng);
        List<List<Object>> rawPlaces = (List<List<Object>>) objects.get(1);
        return rawPlaces.stream()
                .map(this::getPlace)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void sendMessageToGeofenceKafkaTopic(@RequestBody Place place) {
        tile38Service.createPlace(place);
        tile38Service.createPositionHook(place);
    }

}
