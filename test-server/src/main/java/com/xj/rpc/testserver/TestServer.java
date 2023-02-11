package com.xj.rpc.testserver;

import com.xj.rpc.server.RpcServer;

import java.io.IOException;
import java.net.ServerSocket;

public class TestServer {
    public static void main(String[] args) {
        RpcServer rpcServer = new RpcServer();
        rpcServer.register(new HelloServiceImpl());
    }
}
