package com.vacinagora.vacinagora_server;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@SpringBootApplication
@EnableKafkaStreams
public class VacinagoraServerApplication {

	@Bean
	NewTopic positions() {
		return new NewTopic("positions", 6, (short) 1);
	}

	@Bean
	NewTopic positionEvents() {
		return new NewTopic("position_events", 6, (short) 1);
	}

	@Bean
	NewTopic placesCount() {
		return new NewTopic("places_count", 6, (short) 1);
	}

	public static void main(String[] args) {
		SpringApplication.run(VacinagoraServerApplication.class, args);
	}

}
