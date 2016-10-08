package org.darion.yaphet.hadoop.RPC;

public interface GreetingProtocol {
    public static final long versionID = 1L;

    String greeting(String name);
}
