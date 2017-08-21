package com.suprised.dubbo.async;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.rpc.RpcContext;

@Component
public class AsyncInvocationServiceTest {

    @Reference(version = "1.0.0", async = true)// 注解好像不支持单个方法的异步设置，所以使用配置文件处理
    private AsyncInvocationService service;
    
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
            new String[] { "classpath:applicationContext-client.xml" });
        context.start();      
        
        /*AsyncInvocationServiceTest test = (AsyncInvocationServiceTest) context.getBean("asyncInvocationServiceTest");
        System.out.println("异步调用方法：" + test.service.asyncInvocation(2000));
        Future<String> future = RpcContext.getContext().getFuture(); // 拿到调用的Future引用，当结果返回后，会被通知和设置到此Future。
        System.out.println("同步调用方法： " + test.service.syncInvocation(2000));
        
        try {
            String result = future.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }*/
        
        
        AsyncInvocationService service = (AsyncInvocationService) context.getBean("asyncInvocationService");
        System.out.println("异步调用方法：" + service.asyncInvocation(2000));// 直接返回空
        Future<String> future = RpcContext.getContext().getFuture(); // 拿到调用的Future引用，当结果返回后，会被通知和设置到此Future。
        System.out.println("同步调用方法： " + service.syncInvocation(2000));
        
        try {
            String result = future.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
    
}
