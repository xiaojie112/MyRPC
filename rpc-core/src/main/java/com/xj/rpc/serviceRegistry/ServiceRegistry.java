package com.xj.rpc.serviceRegistry;

public interface ServiceRegistry {
    public void register(Object service);
    public Object getService(String name);
}
