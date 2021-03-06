# Company-Controller

## Prerequisites

Add credential definition id in application-dev.yml file
For more information refer network/README.MD

## Development

To start your application in the dev profile, open the terminal, navigate to the `company-controller` folder and run the following commands:

```
docker-compose -f src/main/docker/mongodb.yml up -d
./mvnw
```

The first step will deploy a MongoDB instance. The second step will deploy the application.

## Swagger-Ui

Swagger UI will be available at the following URL

http://localhost:8080/swagger-ui/index.html

Note: The API key can be configured in src/main/resources/config/application-dev.yml (application properties) file which can be used to interact with the API.

## AdminMongo

AdminMongo is running in Port 8081

```
localhost:8081
Connection-Name: Company Controller
Connection-String: mongodb://user123:123pass@docker_companycontroller-mongodb_1:27017/CompanyController?authSource=CompanyController
```

For further instructions on how to develop with JHipster, have a look at [Using JHipster in development][].

## Building Docker Image

To build a docker **dev** image, run:

```
./mvnw package jib:dockerBuild
```

This one creates a new docker image with the name `companycontroller`.

To run the controller + MongoDB and the mongoadmin, run:

```
docker-compose -f src/main/docker/controller-mongo-mongoadmin.yml up -d
```

## Building for production

### Packaging as jar

To build the final jar and optimize the `company-controller` application for production, run:

```
./mvnw -Pprod clean verify
```

To ensure everything worked, run:

```
java -jar target/*.jar

```

Refer to [Using JHipster in production][] for more details.

### Packaging as war

To package your application as a war in order to deploy it to an application server, run:

```
./mvnw -Pprod,war clean verify
```

## Testing

To launch your application's tests, run:

```
./mvnw verify
```

For more information, refer to the [Running tests page][].

### Code quality

Sonar is used to analyse code quality. You can start a local Sonar server (accessible on http://localhost:9001) with:

```
docker-compose -f src/main/docker/sonar.yml up -d
```

You can run a Sonar analysis by using the [sonar-scanner](https://docs.sonarqube.org/display/SCAN/Analyzing+with+SonarQube+Scanner) or by using the Maven plugin.

Then, run a Sonar analysis:

```
./mvnw -Pprod clean verify sonar:sonar
```

If you need to re-run the Sonar phase, please be sure to specify at least the `initialize` phase since Sonar properties are loaded from the sonar-project.properties file.

```
./mvnw initialize sonar:sonar
```

For more information, refer to the [Code quality page][].

## Using Docker to simplify development (optional)

You can use Docker to improve your JHipster development experience. A number of docker-compose configurations are available in the [src/main/docker](src/main/docker) folder to launch required third party services.

For example, to start a MongoDB database in a Docker container, run:

```
docker-compose -f src/main/docker/mongodb.yml up -d
```

To stop it and remove the container, run:

```
docker-compose -f src/main/docker/mongodb.yml down
```

You can also fully dockerize your application and all the services that it depends on.
To achieve this, first build a Docker image of your app by running:

```
./mvnw -Pprod verify jib:dockerBuild
```

Then run:

```
docker-compose -f src/main/docker/app.yml up -d
```

For more information refer to [Using Docker and Docker-Compose][], this page also contains information on the docker-compose sub-generator (`jhipster docker-compose`), which is able to generate Docker configurations for one or several JHipster applications.

## Continuous Integration (optional)

To configure CI for your project, run the ci-cd sub-generator (`jhipster ci-cd`), this will let you generate configuration files for a number of Continuous Integration systems. Consult the [Setting up Continuous Integration][] page for more information.

[jhipster homepage and latest documentation]: https://www.jhipster.tech
[jhipster 6.10.5 archive]: https://www.jhipster.tech/documentation-archive/v6.10.5
[using jhipster in development]: https://www.jhipster.tech/documentation-archive/v6.10.5/development/
[using docker and docker-compose]: https://www.jhipster.tech/documentation-archive/v6.10.5/docker-compose
[using jhipster in production]: https://www.jhipster.tech/documentation-archive/v6.10.5/production/
[running tests page]: https://www.jhipster.tech/documentation-archive/v6.10.5/running-tests/
[code quality page]: https://www.jhipster.tech/documentation-archive/v6.10.5/code-quality/
[setting up continuous integration]: https://www.jhipster.tech/documentation-archive/v6.10.5/setting-up-ci/
