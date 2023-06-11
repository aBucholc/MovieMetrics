package com.example.moviemetrics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@ComponentScan(basePackages = "com.example.moviemetrics")
public class MovieMetricsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieMetricsApplication.class, args);
	}

}
