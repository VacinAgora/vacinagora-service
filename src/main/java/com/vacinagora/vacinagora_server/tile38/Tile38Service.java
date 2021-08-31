package com.vacinagora.vacinagora_server.tile38;

import com.vacinagora.vacinagora_server.models.Place;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.codec.StringCodec;
import io.lettuce.core.output.NestedMultiOutput;
import io.lettuce.core.protocol.CommandArgs;
import io.lettuce.core.protocol.CommandType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Tile38Service {

    private final Logger logger = LoggerFactory.getLogger(Tile38Service.class);

    private static final String TILE38_URL = "redis://localhost:9851";
//    private static final String EVENTS_URL = "http://host.docker.internal:9000/kafka/publish/events";
    private static final String EVENTS_URL = "kafka://broker:9092/position_events";

    private static final StringCodec codec = StringCodec.UTF8;

    private final RedisClient client;

    public Tile38Service() {
        client = RedisClient.create(TILE38_URL);
    }

    public void createPositionHook(Place place) {
        try (StatefulRedisConnection<String, String> connection = client.connect()) {
            RedisCommands<String, String> sync = connection.sync();

            String placeId = place.getPlaceId();
            int range = "LARGE".equals(place.getSize()) ? 1000 : 500;

            logger.info(String.format("#### -> Creating position hook -> %s", placeId));

            CommandArgs<String, String> args = new CommandArgs<>(codec)
                    .addKey(placeId)
                    .add(EVENTS_URL)
                    .add(Tile38Keywords.WITHIN)
                    .add("position")
                    .add(Tile38Keywords.FENCE)
                    .add(Tile38Keywords.CIRCLE)
                    .add(place.getLatitude())
                    .add(place.getLongitude())
                    .add(range);

            sync.dispatch(Tile38Keywords.SETHOOK, new NestedMultiOutput<>(codec), args);
        }
    }

    public void createPlace(Place place) {
        try (StatefulRedisConnection<String, String> connection = client.connect()) {
            RedisCommands<String, String> sync = connection.sync();

            String placeId = place.getPlaceId();

            logger.info(String.format("#### -> Creating place -> %s", placeId));

            CommandArgs<String, String> args = new CommandArgs<>(codec)
                    .addKey("place")
                    .add(placeId)
                    .add(Tile38Keywords.POINT)
                    .add(place.getLatitude())
                    .add(place.getLongitude());

            sync.dispatch(CommandType.SET, new NestedMultiOutput<>(codec), args);
        }
    }

    public List<Object> listPlacesWithinBounds(Double swLat, Double swLng, Double neLat, Double neLng) {
        try (StatefulRedisConnection<String, String> connection = client.connect()) {
            RedisCommands<String, String> sync = connection.sync();

            CommandArgs<String, String> args = new CommandArgs<>(codec)
                    .add("place")
                    .add(Tile38Keywords.BOUNDS)
                    .add(swLat)
                    .add(swLng)
                    .add(neLat)
                    .add(neLng);

            return sync.dispatch(Tile38Keywords.WITHIN, new NestedMultiOutput<>(codec), args);
        }
    }

}
