package com.suprised.dubbo.event;

public interface ClientEvent {

    /**
     * ����֮ǰִ�� 
     */
    public void oninvoke(String param);
    
    /**
     * ����֮ǰִ�� 
     */
    public void onreturn(String result, String param);
    
    /**
     * �׳��쳣֮ǰִ�� 
     */
    public void onthrow(Throwable e, String param);
    
}
