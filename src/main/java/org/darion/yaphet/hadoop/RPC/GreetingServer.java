package org.darion.yaphet.hadoop.RPC;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.ipc.Server;

import java.io.IOException;

public class GreetingServer {

    public static void main(String[] args) throws IOException {
        RPC.Builder builder = new RPC.Builder(new Configuration());

        Server server = builder
                .setBindAddress("127.0.0.1")
                .setPort(8080)
                .setInstance(new GreetingProtocolImpl())
                .setProtocol(GreetingProtocol.class)
                .build();
        server.start();
    }
}
