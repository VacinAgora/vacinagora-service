package com.vacinagora.vacinagora_server.service;

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

        builder.stream("warehouse", Consumed.with(stringSerde, stringSerde))
                .print(Printed.toSysOut());
//                .foreach();
//        quotes.print(Printed.toSysOut());
//        quotes.to("counts");
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
