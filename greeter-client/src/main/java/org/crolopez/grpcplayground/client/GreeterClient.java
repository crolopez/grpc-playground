package org.crolopez.grpcplayground.client;

import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.crolopez.grpcplayground.greeter.GreeterGrpc;
import org.crolopez.grpcplayground.greeter.HelloReply;
import org.crolopez.grpcplayground.greeter.HelloRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class GreeterClient {

    private static final Logger log = LoggerFactory.getLogger(GreeterClient.class);

    public static void main(String[] args) {
        SpringApplication.run(GreeterClient.class, args);
    }

    @Component
    static class GreeterRunner implements CommandLineRunner {

        @GrpcClient("greeter")
        private GreeterGrpc.GreeterBlockingStub greeterStub;

        @Override
        public void run(String... args) throws Exception {
            String name = args.length > 0 ? args[0] : "Spring Boot Client";
            log.info("Will try to greet {} ...", name);

            HelloRequest request = HelloRequest.newBuilder().setName(name).build();
            HelloReply reply;
            try {
                reply = greeterStub.sayHello(request);
                log.info("Greeting received: {}", reply.getMessage());
                System.exit(0);
            } catch (StatusRuntimeException e) {
                log.error("RPC failed: {}", e.getStatus());
                System.exit(1);
            }
        }
    }
}
