package com.fontys.marketplace_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Note to self: -p [host port]:[container port] (maps container port to host port)

/*
--- Solution - Dockerfile ---

FROM amazoncorretto:17
ADD target/back-end.jar /opt/app.jar
CMD ["java", "-jar", "/opt/app.jar"]

--- Solution - build and run ---

# maven build the project
$ cd back-end
$ mvn verify

# Build the image and 'tag' it with the name 'back-end'
$ docker build -t back-end .

# Run it by image-name and give the container also the name 'back-end'
# port bind host port 10000 to container port 8080 where payara runs.
$ docker run -d --rm --name back-end -p 10000:8080 back-end

--- Solution - Stopping the container ---

# Clean stop
docker stop back-end
#or just stop and remove
docker rm -f back-end
 */

/* Useful commands:
 * Build project:
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
 * Stop container
 * docker stop marketplace-backend
 *
 * MySQL docker:
 * docker run --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -d mysql
 *
 * SonarQube docker:
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
