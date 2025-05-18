package com.challang.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing
@SpringBootApplication
public class ChallangBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallangBackendApplication.class, args);
	}

}
