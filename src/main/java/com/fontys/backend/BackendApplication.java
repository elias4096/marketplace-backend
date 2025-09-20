package com.fontys.backend;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Objects;

/*
com
└── example
    ├── demo
    │   ├── controller
    │   ├── service
    │   ├── repository
    │   ├── model
    │   ├── dto
    │   ├── exception
    │   └── config
    └── DemoApplication.java
 */

@SpringBootApplication
public class BackendApplication {
	public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        System.setProperty("DATABASE_URL", Objects.requireNonNull(dotenv.get("DATABASE_URL")));
        System.setProperty("DATABASE_USERNAME", Objects.requireNonNull(dotenv.get("DATABASE_USERNAME")));
        System.setProperty("DATABASE_PASSWORD", Objects.requireNonNull(dotenv.get("DATABASE_PASSWORD")));

		SpringApplication.run(BackendApplication.class, args);
	}
}
