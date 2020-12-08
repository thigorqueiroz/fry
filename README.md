# fry
Fry is a backend microservice to manage our campaigns.
=======
<p align="center">
  <img src="https://media1.tenor.com/images/28f49f4fcfbe4631ec685cccb88d3a99/tenor.gif?itemid=4691459" height="350" />
</p>


# Fry

> Fry is the main protagonist of the animated sitcom Futurama;

Fry is a backend microservice to manage our campaigns. 

## Why Fry?

In my **challenge** journey, I think that could be a good name to be here.

## Table of contents
- [Architecture](#Architecture)
- [Prerequisites](#Prerequisites)
  - [Environment](#Environment)
  - [Ide](#Ide)
  - [Installing](#Installing)
- [Testing](#Testing)
  - [Running](#Running)
- [Built with](#Built-with)

## Architecture
The model architecture for this project is based on Hexagonal (Port and Adapters)

![hexagonal](https://paulovich.net/img/hexagonal-1.png)

### Strategic Design Patterns
* Value Object
* Entity
* Aggregate (Root)
* Repository
* Domain Event

## Prerequisites

### Environment

- [Java 11 or later](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)


### Ide

- [IntelliJ IDEA](https://www.jetbrains.com/idea/download/#section=linux)

### Installing

```
# clone this repository

git clone https://github.com/thigorqueiroz/fry.git

# To run your app and tests locally you must start the containers using docker-compose 
:> cd docker && docker-compose up -d


# open in IntelliJ IDEA

# run the project with the green play button

```

## Testing

This project uses [JUnit](https://junit.org/junit5/docs/current/user-guide/) as testing framework.

### Running
Open a test file and run with the green play button

## Developing

Into your IntelliJ IDEA, to run the project, you should optimally set a Configuration to run with the following environment variable set to get all help from local-oriented settings.
`SPRING_PROFILES_ACTIVE=local`

### Environment

#### local start 
    # default is 8080
    export SPRING_SERVER_PORT=8081 
	gradle bootRun
  
#### /health
	http localhost:8080/actuator/health

### Database

#### Index naming



## Built With

[**Kotlin**](https://kotlinlang.org/docs/reference/server-overview.html) - Kotlin is a great fit for developing server-side applications, allowing you to write concise and expressive code while maintaining full compatibility with existing Java-based technology stacks and a smooth learning curve:

[**Spring boot**](https://spring.io/projects/spring-boot) - Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications that you can "just run".

[**JUnit**](https://junit.org/) - JUnit 5 is the next generation of JUnit. The goal is to create an up-to-date foundation for developer-side testing on the JVM.

## Working With RabbitMq
Since this project use RabbitMq, as you start all dependency containers via Docker Compose, you can access [RabbitMq Management](http://localhost:15672) for a RabbitMQ Management UI.

