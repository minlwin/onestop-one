package com.jdc.one.traders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TradersApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradersApiApplication.class, args);
	}

}
