package com.fantasyhelper.fantasyhelper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@EnableAspectJAutoProxy //I am adding this because of @ControllerAdvance
@EnableJpaRepositories(basePackages = "com.fantasyhelper.fantasyhelper.repository")
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })

public class FantasyhelperApplication {

	public static void main(String[] args) {
		SpringApplication.run(FantasyhelperApplication.class, args);
	}
}
