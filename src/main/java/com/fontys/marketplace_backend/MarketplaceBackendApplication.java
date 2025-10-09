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
 * Run container:
 * docker run -p 9090:8080 marketplace-backend
 *
 * Stop container
 * docker stop marketplace-backend
 *
 * Build and run sonarqube docker image:
 * docker run --name sonarqube -p 9000:9000 sonarqube:community
 *
 * Run sonarqube
 * gradlew sonar -Dsonar.token=???
 */

@SpringBootApplication
public class MarketplaceBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketplaceBackendApplication.class, args);
	}

}
