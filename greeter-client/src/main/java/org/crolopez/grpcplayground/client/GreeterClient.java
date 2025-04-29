package org.crolopez.grpcplayground.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import java.io.Closeable;
import org.crolopez.grpcplayground.greeter.GreeterGrpc;
import org.crolopez.grpcplayground.greeter.HelloReply;
import org.crolopez.grpcplayground.greeter.HelloRequest;
import io.grpc.StatusRuntimeException;

public class GreeterClient implements Closeable {
    private final GreeterGrpc.GreeterBlockingStub stub;
    private final ManagedChannel channel;

    GreeterClient() {
        channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();
        stub = GreeterGrpc.newBlockingStub(channel);
    }

    void greet(String name) {
        HelloRequest request = HelloRequest.newBuilder().setName(name).build();
        HelloReply reply;
        try {
            reply = stub.sayHello(request);
            System.out.println("Greeting: " + reply.getMessage());
        } catch (StatusRuntimeException e) {
            System.err.println("RPC failed: " + e.getStatus());
            return;
        }
    }

    public static void main(String[] args) throws Exception {
        GreeterClient c = new GreeterClient();
        try {
            String user = args.length > 0 ? args[0] : "World";
            c.greet(user);
        } finally {
            c.shutdown();
        }
    }

    @Override
    public void close() {
        channel.shutdownNow();
    }

    void shutdown() {
        channel.shutdownNow();
    }
}
