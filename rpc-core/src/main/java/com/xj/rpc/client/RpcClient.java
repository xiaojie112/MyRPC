package com.xj.rpc.client;

import com.xj.common.entity.RpcRequest;
import com.xj.common.entity.RpcResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RpcClient {
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
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            RpcResponse response = (RpcResponse)inputStream.readObject();
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
