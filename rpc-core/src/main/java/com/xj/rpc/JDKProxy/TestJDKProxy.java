package com.xj.rpc.JDKProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TestJDKProxy {
    public static void main(String[] args) {
        EmailImpl email = new EmailImpl();
        Email proxy = (Email) EmailProxyFactory.getProxy(email);
        proxy.sendEmail();
    }
}

interface Email{
    public void sendEmail();
}

class EmailImpl implements Email{

    @Override
    public void sendEmail() {
        System.out.println("发送邮箱成功!");
    }

    public void test1(){
        System.out.println("test1成功");
    }

}

class EmailImplProxy implements InvocationHandler{

    private final Object origin;

    EmailImplProxy(Object origin) {
        this.origin = origin;
    }


    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        System.out.println("调用原生方法之前");
        Object invoke = method.invoke(origin, objects);
        System.out.println("调用原生方法之后");
        System.out.println("添加自己的操作");
        return invoke;
    }
}


class EmailProxyFactory{
    public static Object getProxy(Object object){
        return Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), new EmailImplProxy(object));
    }
}