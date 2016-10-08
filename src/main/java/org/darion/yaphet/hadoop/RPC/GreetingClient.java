package org.darion.yaphet.hadoop.RPC;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.net.SocksSocketFactory;

import java.io.IOException;
import java.net.InetSocketAddress;

public class GreetingClient {
    public static void main(String[] args) throws IOException {
        GreetingProtocol service = RPC.getProxy(
                GreetingProtocol.class,
                1L,
                new InetSocketAddress("127.0.0.1", 8080),
                new Configuration(),
                new SocksSocketFactory());

        System.out.println(service.greeting("darion.yaphet"));
        RPC.stopProxy(service);
    }
}
