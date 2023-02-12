package com.xj.rpc.server;

import com.xj.common.entity.RpcRequest;
import com.xj.common.entity.RpcResponse;
import com.xj.rpc.serviceRegistry.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

public class WorkerThread implements Runnable {
    private Socket socket;
    private ServiceRegistry serviceRegistry;
    private static final Logger logger = LoggerFactory.getLogger(WorkerThread.class);

    public WorkerThread(Socket socket, ServiceRegistry serviceRegistry) {
        this.socket = socket;
        this.serviceRegistry = serviceRegistry;
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            RpcRequest rpcRequest = ((RpcRequest) inputStream.readObject());
            String interfaceName = rpcRequest.getInterfaceName();
            //到服务注册表中找到对应的服务
            Object service = serviceRegistry.getService(interfaceName);
            //通过方法名找到对应的方法
            Method method = service.getClass().getMethod(rpcRequest.getMethodName(), rpcRequest.getParameterTypes());
            Object invoke = method.invoke(service, rpcRequest.getParameters());
            RpcResponse rpcResponse = new RpcResponse();
            rpcResponse.success(invoke);
            logger.info("服务{}调用{}方法成功!", service, method);
            outputStream.writeObject(rpcResponse);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
