package com.suprised.dubbo.async;

public interface AsyncInvocationService {

    /**
     * �첽���÷��� 
     */
    public String asyncInvocation(long waitTime);
    
    /**
     * ͬ�����÷��� 
     */
    public String syncInvocation(long waitTime);
}
