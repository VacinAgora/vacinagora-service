package com.vacinagora.vacinagora_server.serdes;

import com.vacinagora.vacinagora_server.models.PlaceCount;
import com.vacinagora.vacinagora_server.models.PositionEvent;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;

public final class CustomSerdes {

    static public final class PositionEventSerde extends Serdes.WrapperSerde<PositionEvent> {
        public PositionEventSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>(PositionEvent.class));
        }
    }

    static public final class PlaceCountSerde extends Serdes.WrapperSerde<PlaceCount> {
        public PlaceCountSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>(PlaceCount.class));
        }
    }

    public static Serde<PositionEvent> PositionEvent() {
        return new CustomSerdes.PositionEventSerde();
    }

    public static Serde<PlaceCount> PlaceCount() {
        return new CustomSerdes.PlaceCountSerde();
    }
}
