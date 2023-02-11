package com.xj.rpc.testclient;

import com.xj.rpc.api.HelloObject;
import com.xj.rpc.api.HelloService;
import com.xj.rpc.client.ClientProxyFactory;

public class TestClient {
    public static void main(String[] args) {
//        HelloService helloService = new HelloService();报错，本地并没有实现
        ClientProxyFactory clientProxyFactory = new ClientProxyFactory(9000, "127.0.0.1");
        HelloService helloService = (HelloService) clientProxyFactory.getProxy(HelloService.class);
        String hello = helloService.hello(new HelloObject(1,"测试消息"));
        System.out.println(hello);

        Class<TestClient> c = TestClient.class;
        Class<? extends Class> aClass = c.getClass();
    }
}
