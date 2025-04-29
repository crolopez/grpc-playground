package org.crolopez.grpcplayground.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import org.crolopez.grpcplayground.greeter.GreeterGrpc;
import org.crolopez.grpcplayground.greeter.HelloReply;
import org.crolopez.grpcplayground.greeter.HelloRequest;

public class GreeterServer {
    private Server server;

    private void start() throws IOException {
        server = ServerBuilder.forPort(50051)
                .addService(new GreeterImpl())
                .build()
                .start();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("Shutting down gRPC server...");
            GreeterServer.this.stop();
        }));
        System.out.println("Server started on port " + server.getPort());
    }

    private void stop() {
        if (server != null)
            server.shutdown();
    }

    static class GreeterImpl extends GreeterGrpc.GreeterImplBase {
        @Override
        public void sayHello(HelloRequest req,
                StreamObserver<HelloReply> responseObserver) {
            HelloReply reply = HelloReply.newBuilder()
                    .setMessage("Hello, " + req.getName())
                    .build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        GreeterServer server = new GreeterServer();
        server.start();
        server.server.awaitTermination();
    }
}
