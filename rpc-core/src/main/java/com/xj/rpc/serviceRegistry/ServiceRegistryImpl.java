package com.xj.rpc.serviceRegistry;

import com.xj.common.enumeration.RpcError;
import com.xj.common.exception.RpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ServiceRegistryImpl implements ServiceRegistry{
    private static final Logger logger = LoggerFactory.getLogger(ServiceRegistryImpl.class);

    //便于通过接口名寻找到对应的服务
    private final ConcurrentHashMap<String, Object> serviceMap = new ConcurrentHashMap<>();

    //避免重复注册服务
    //TODO 此处创建的keySet和普通的set有什么区别?
    private final Set<String> serviceRegistered = ConcurrentHashMap.newKeySet();

    @Override
    public void register(Object service) {
        String serviceName = service.getClass().getCanonicalName();
        if(serviceRegistered.contains(serviceName))return;
        serviceRegistered.add(serviceName);
        Class[] interfaces = service.getClass().getInterfaces();
        if(interfaces == null || interfaces.length == 0){
            throw new RpcException(RpcError.SERVICE_NOT_IMPLEMENT_ANY_INTERFACE);
        }

        for (Class anInterface : interfaces) {
            String interfaceName = anInterface.getCanonicalName();
            serviceMap.put(interfaceName, service);
        }
        logger.info("向接口:{}注册服务{}", interfaces, service);
    }

    @Override
    public Object getService(String name) {
        Object service = serviceMap.get(name);
        if(service == null) {
            throw new RpcException(RpcError.SERVICE_NOT_FOUND);
        }
        return service;
    }
}
