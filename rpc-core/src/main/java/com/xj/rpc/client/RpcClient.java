package com.xj.rpc.client;

import com.xj.common.entity.RpcRequest;
import com.xj.common.entity.RpcResponse;
import com.xj.rpc.server.WorkerThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RpcClient {
    //TODO 此处参数没有填写RpcClient.class有什么问题?
    private static final Logger logger = LoggerFactory.getLogger(WorkerThread.class);
    public RpcResponse sendRequest(String ip, int port, RpcRequest rpcRequest){
        try {
            Socket socket = new Socket(ip, port);

//TODO ObjectOutputStream和OutputStream的关系
//            OutputStream outputStream1 = socket.getOutputStream();
//            outputStream1.write(rpcRequest);

//            ObjectOutputStream outputStream = (ObjectOutputStream) socket.getOutputStream();exception
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(rpcRequest);

//TODO flush?
            outputStream.flush();
            logger.info("客户端{}:{}成功发送消息{}", socket.getInetAddress(),socket.getPort(), rpcRequest);
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            RpcResponse response = (RpcResponse)inputStream.readObject();
            logger.info("客户端{}:{}成功接收消息{}", socket.getInetAddress(),socket.getPort(), response);
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
