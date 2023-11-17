package com.db.project.ugadining;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UgaDiningCommonsApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure().load();

		System.setProperty("POSTGRES_USERNAME", dotenv.get("POSTGRES_USERNAME"));
		System.setProperty("POSTGRES_PASSWORD", dotenv.get("POSTGRES_PASSWORD"));
		System.setProperty("POSTGRES_URL", dotenv.get("POSTGRES_URL"));

		SpringApplication.run(UgaDiningCommonsApplication.class, args);
	}

}
