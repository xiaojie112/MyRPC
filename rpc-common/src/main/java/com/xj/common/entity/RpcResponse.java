package com.xj.common.entity;

import com.xj.common.enumeration.RpcResponseCode;
import lombok.Data;

import java.io.Serializable;

@Data
public class RpcResponse implements Serializable {
    private int statusCode;
    private String messageForStatus;
    private Object data;

    public void success(Object data){
        statusCode = RpcResponseCode.SUCCESS.getStatusCode();
        messageForStatus = RpcResponseCode.SUCCESS.getMessageForCode();
        this.data = data;
    }

    public void fail(){
        statusCode = RpcResponseCode.FAIL.getStatusCode();
        messageForStatus = RpcResponseCode.FAIL.getMessageForCode();
    }
}
