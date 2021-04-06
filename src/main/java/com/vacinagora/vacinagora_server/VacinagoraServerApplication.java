package com.vacinagora.vacinagora_server;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@SpringBootApplication
@EnableKafkaStreams
public class VacinagoraServerApplication {

//	@Bean
//	NewTopic quotes() {
//		return new NewTopic("quotes", 6, (short) 1);
//	}
//
//	@Bean
//	NewTopic counts() {
//		return new NewTopic("counts", 6, (short) 1);
//	}

	@Bean
	NewTopic positions() {
		return new NewTopic("positions", 6, (short) 1);
	}

	@Bean
	NewTopic places() {
		return new NewTopic("places", 6, (short) 1);
	}

	@Bean
	NewTopic warehouse() {
		return new NewTopic("warehouse", 6, (short) 1);
	}

	public static void main(String[] args) {
		SpringApplication.run(VacinagoraServerApplication.class, args);
	}

}
