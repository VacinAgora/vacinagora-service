package com.vacinagora.vacinagora_server.service;

import com.vacinagora.vacinagora_server.models.PositionEvent;
import com.vacinagora.vacinagora_server.serdes.CustomSerdes;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Printed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class Processor {

    @Autowired
    public void process(final StreamsBuilder builder) {
        final Serde<String> stringSerde = Serdes.String();
        final Serde<PositionEvent> positionEventSerde = CustomSerdes.PositionEvent();
//        final Serde<PlaceCount> placeCountSerde = CustomSerdes.PlaceCount();

        builder.stream("places_count", Consumed.with(stringSerde, positionEventSerde))
                .print(Printed.toSysOut());

//        final KTable<String, PlaceCount> counts = source
//                .groupBy((key, value) -> value. getHook())
//                .count()
//                .mapValues(PlaceCount::new);
//
//        // need to override value serde to GenreCount type
//        counts.toStream()
//                .to("places_count", Produced.with(stringSerde, placeCountSerde));
    }

//    @Autowired
//    public void process(final StreamsBuilder builder) {
//        final Serde<String> stringSerde = Serdes.String();
//
//        KStream<String, Long> quotes = builder.stream("quotes", Consumed.with(stringSerde, stringSerde))
//                .flatMapValues(value -> Arrays.asList(value.toLowerCase().split("\\W+")))
//                .groupBy((key, value) -> value, Grouped.with(stringSerde, stringSerde))
//                .count()
//                .toStream();
//        quotes.print(Printed.toSysOut());
//        quotes.to("counts");
//    }

}
