package com.fontys.marketplace_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Note: -p [host port]:[container port] (maps container port to host port)

/*
 * gradlew bootRun
 * 
 *  Build project:
 * ./gradlew assemble
 *
 * Test project:
 * ./gradlew test
 *
 * Build and test:
 * ./gradlew build
 *
 * Build docker image:
 * docker build -t marketplace-backend .
 *
 * Run container:
 * docker run -p 9090:8080 marketplace-backend
 *
 * Stop container:
 * docker stop marketplace-backend
 *
 * MySQL docker:
 * docker run --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -d mysql
 *
 * SonarQube docker:
 * docker run --name sonarqube -p 9000:9000 sonarqube:community
 *
 * Run sonarqube:
 * gradlew sonar -Dsonar.token=[token]
 */

@SpringBootApplication
public class MarketplaceBackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(MarketplaceBackendApplication.class, args);
	}
}
