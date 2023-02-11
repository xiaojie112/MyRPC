package com.xj.rpc.api;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class HelloObject implements Serializable {//TODO 为什么需要实现序列化接口
    private Integer id;
    private String message;
}


