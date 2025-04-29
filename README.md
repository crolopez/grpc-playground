# gRPC Playground - Hello World

This project is a simple proof-of-concept for learning and experimenting with the gRPC framework. It implements a basic "Hello World" service where a client sends a name to a server, and the server responds with a personalized greeting.

## Project Structure

This is a multi-module Maven project:

-  `grpc-playground-parent`: The parent POM defining common configurations and dependency management.
-  `proto-api`: Contains the Protobuf service definition (`greeter.proto`) and handles the generation of Java code from it. Other modules depend on this artifact.
-  `greeter-server`: Implements the gRPC server exposing the `Greeter` service.
-  `greeter-client`: Implements a simple client that connects to the server and calls the `SayHello` method.

## Prerequisites

-  JDK (Java Development Kit) - Version 11 or higher recommended.
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
mvn -pl greeter-server exec:java "-Dexec.mainClass=org.crolopez.grpcplayground.server.GreeterServer"
```

The server will start and listen for incoming connections on port `50051` (default). You should see output indicating the server has started.

### 2. Run the Client

Open **another terminal** and, from the project's root directory, execute:

```bash
mvn -pl greeter-client exec:java "-Dexec.mainClass=org.crolopez.grpcplayground.client.GreeterClient"
```

The client will run, send a request to the server (using the name "World" by default), and print the response received from the server to the console, similar to this:
