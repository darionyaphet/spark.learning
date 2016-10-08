package org.darion.yaphet.hadoop.RPC;

public class GreetingProtocolImpl implements GreetingProtocol {
    @Override
    public String greeting(String name) {
        return "Hello " + name + " !";
    }
}
