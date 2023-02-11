package com.xj.rpc.testserver;

import com.xj.rpc.api.HelloObject;
import com.xj.rpc.api.HelloService;

public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(HelloObject helloObject) {
        return helloObject.getMessage();
    }
}
