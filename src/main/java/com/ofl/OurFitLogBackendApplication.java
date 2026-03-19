package com.ofl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class OurFitLogBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(OurFitLogBackendApplication.class, args);
	}

}
