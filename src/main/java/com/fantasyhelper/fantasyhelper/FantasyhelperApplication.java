package com.fantasyhelper.fantasyhelper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.fantasyhelper.fantasyhelper.repository")
@SpringBootApplication
public class FantasyhelperApplication {

	public static void main(String[] args) {
		SpringApplication.run(FantasyhelperApplication.class, args);
	}
}
