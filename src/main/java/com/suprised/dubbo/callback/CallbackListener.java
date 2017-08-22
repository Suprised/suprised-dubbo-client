package com.suprised.dubbo.callback;

/**
 * 定义接口，由客户端实现,服务端回调客户端的实现。 
 */
public interface CallbackListener {

    void changed(String msg);
    
}
