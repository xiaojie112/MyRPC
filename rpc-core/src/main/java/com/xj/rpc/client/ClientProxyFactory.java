package com.xj.rpc.client;

import com.xj.common.entity.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ClientProxyFactory implements InvocationHandler {
    public int port;
    public String ip;

    public ClientProxyFactory(int port, String ip) {
        this.port = port;
        this.ip = ip;
    }

    //TODO 使用泛型和Object之间的区别
    public Object getProxy(Class target){
//        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),this);
        //TODO 泛型通配符在这里的作用
        return Proxy.newProxyInstance(target.getClassLoader(), new Class[]{target},this);
    }

    @Override
    public Object invoke(Object o, Method method, Object[] args) throws Throwable {
        RpcRequest rpcRequest = RpcRequest.builder()
                .interfaceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .parameters(args)
                .parameterTypes(method.getParameterTypes())
                .build();

        return new RpcClient().sendRequest(ip, port, rpcRequest).getData();
    }
}
