package com.fontys.marketplace_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/* Useful commands:
 * Build project:
 * ./gradlew assemble
 *
 * Build, run tests & static code analysis:
 * ./gradlew build
 *
 * See docker images:
 * docker images
 *
 * Build docker image:
 * docker build -t marketplace-backend .
 *
 * Run image:
 * docker run -p 9090:8080 marketplace-backend
 */

@SpringBootApplication
public class MarketplaceBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketplaceBackendApplication.class, args);
	}

}
