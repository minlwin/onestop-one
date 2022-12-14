package com.jdc.onestop.directory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@PropertySource(value = "classpath:/adminuser.properties")
public class ServiceDirectoryApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceDirectoryApiApplication.class, args);
	}

}
