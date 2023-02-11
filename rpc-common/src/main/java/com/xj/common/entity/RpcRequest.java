package com.xj.common.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class RpcRequest implements Serializable {
    //接口名
    private String interfaceName;

    //请求方法名
    private String methodName;

    //请求参数类型
    private Class[] parameterTypes;

    //请求参数
    private Object[] parameters;

}
