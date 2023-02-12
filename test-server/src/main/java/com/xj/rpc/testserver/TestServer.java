package com.xj.rpc.testserver;

import com.xj.rpc.server.RpcServer;
import com.xj.rpc.serviceRegistry.ServiceRegistry;
import com.xj.rpc.serviceRegistry.ServiceRegistryImpl;

import java.io.IOException;
import java.net.ServerSocket;

public class TestServer{
    public static void main(String[] args) {
        ServiceRegistryImpl serviceRegistry = new ServiceRegistryImpl();
        serviceRegistry.register(new HelloServiceImpl());
        RpcServer rpcServer = new RpcServer(serviceRegistry);
        rpcServer.start();
    }
}
