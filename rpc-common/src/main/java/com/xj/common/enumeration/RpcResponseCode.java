package com.xj.common.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RpcResponseCode {
    //TODO 为什么必须要列在最上面?
    SUCCESS(100, "调用成功"),
    FAIL(200, "调用失败"),
    NOTFOUND_CLASSNAME(300,"没有找到类"),
    NOTFOUND_METHODNAME(400, "没有找到方法");

    private final int statusCode;
    private final String messageForCode;



}
