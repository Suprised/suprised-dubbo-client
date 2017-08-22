package com.suprised.dubbo.callback;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;

@Component
public class CallbackServiceTest {

    @Reference
    private CallbackService callbackService;
    
    public static void main(String[] args) {
        
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
            new String[] { "classpath:applicationContext-client.xml" });
        context.start();
        
        CallbackServiceTest test = (CallbackServiceTest) context.getBean("callbackServiceTest");
        test.getCallbackService().addListener("CallbackServiceTestCallback", new CallbackListener() {
            
            @Override
            public void changed(String msg) {
                System.out.println("回调的参数： " + msg);
            }
        });
        
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public CallbackService getCallbackService() {
        return callbackService;
    }

    public void setCallbackService(CallbackService callbackService) {
        this.callbackService = callbackService;
    }
    
}
