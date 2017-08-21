package com.suprised.dubbo.async;

public interface AsyncInvocationService {

    /**
     * 异步调用方法 
     */
    public String asyncInvocation(long waitTime);
    
    /**
     * 同步调用方法 
     */
    public String syncInvocation(long waitTime);
}
