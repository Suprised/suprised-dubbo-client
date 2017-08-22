package com.suprised.dubbo.event.impl;

import com.suprised.dubbo.event.ClientEvent;

/**
 * 客户端调用时的事件通知
 */
public class ClientEventImpl implements ClientEvent {

    @Override
    public void oninvoke(String param) {
        System.out.println("client: oninvoke(), param=" + param);
    }

    @Override
    public void onreturn(String result, String param) {
        System.out.println("client: onreturn(), result=" + result + ", param="  + param);
    }

    @Override
    public void onthrow(Throwable e, String param) {
        System.out.println("client: onthrow(), param=" + param);
        System.out.println("异常消息：" + e.getMessage());
    }

}
