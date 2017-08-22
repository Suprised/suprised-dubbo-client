package com.suprised.dubbo.callback;

public interface CallbackService {

    public void addListener(String key, CallbackListener listener);
    
}
