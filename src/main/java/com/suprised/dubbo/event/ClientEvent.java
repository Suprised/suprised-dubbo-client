package com.suprised.dubbo.event;

public interface ClientEvent {

    /**
     * 调用之前执行 
     */
    public void oninvoke(String param);
    
    /**
     * 返回之前执行 
     */
    public void onreturn(String result, String param);
    
    /**
     * 抛出异常之前执行 
     */
    public void onthrow(Throwable e, String param);
    
}
