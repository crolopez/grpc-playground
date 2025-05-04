# gRPC Playground - Hello World

This project is a simple proof-of-concept for learning and experimenting with the gRPC framework. It implements a basic "Hello World" service where a client sends a name to a server, and the server responds with a personalized greeting.

## Project Structure

This is a multi-module Maven project:

-  `grpc-playground-parent`: The parent POM defining common configurations and dependency management.
-  `proto-api`: Contains the Protobuf service definition (`greeter.proto`) and handles the generation of Java code from it. Other modules depend on this artifact.
-  `greeter-server`: Implements the gRPC server exposing the `Greeter` service.
-  `greeter-client`: Implements a simple client that connects to the server and calls the `SayHello` method.

## Prerequisites

-  JDK (Java Development Kit) - Version 17 or higher recommended.
-  Apache Maven - For building and running the project.

## Building the Project

To compile all modules and generate the necessary gRPC/Protobuf code, run the following command from the project's root directory (`grpc-playground`):

```bash
mvn clean install
```

This will clean previous builds, generate code from `proto-api`, compile the server and client, and package the artifacts.

## Running the Example

You need to run the server first, followed by the client in separate terminals.

### 1. Run the Server

From the project's root directory, execute:

```bash
mvn -pl greeter-server spring-boot:run
```

The server will start using Spring Boot, and listen for incoming connections on port `50051` (as configured in `greeter-server/src/main/resources/application.yml`). You should see Spring Boot logs followed by gRPC server startup messages.

### 2. Run the Client

Open **another terminal** and, from the project's root directory, execute:

```bash
mvn -pl greeter-client spring-boot:run
```

The client will run as a Spring Boot application, automatically connect to the server configured in `greeter-client/src/main/resources/application.yml`, send a request, print log, and then exit.

You should see output similar to this in the client's console (mixed with Spring Boot logs):

```log
INFO ... o.c.grpcplayground.client.GreeterClient : Will try to greet Spring Boot Client ...
INFO ... o.c.grpcplayground.client.GreeterClient : Greeting received: Hello, Spring Boot Client
```