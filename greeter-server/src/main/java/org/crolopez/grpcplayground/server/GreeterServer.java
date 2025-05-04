package org.crolopez.grpcplayground.server;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.crolopez.grpcplayground.greeter.GreeterGrpc;
import org.crolopez.grpcplayground.greeter.HelloReply;
import org.crolopez.grpcplayground.greeter.HelloRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GreeterServer {

    public static void main(String[] args) {
        SpringApplication.run(GreeterServer.class, args);
    }

    @GrpcService
    static class GreeterImpl extends GreeterGrpc.GreeterImplBase {

        private static final Logger serviceLog = LoggerFactory.getLogger(GreeterImpl.class);

        @Override
        public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
            serviceLog.info("Received request for name: {}", req.getName());
            HelloReply reply = HelloReply.newBuilder()
                    .setMessage("Hello, " + req.getName())
                    .build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
            serviceLog.info("Sent response for name: {}", req.getName());
        }
    }
}
