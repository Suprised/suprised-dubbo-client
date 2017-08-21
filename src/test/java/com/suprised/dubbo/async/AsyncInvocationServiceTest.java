package com.suprised.dubbo.async;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.rpc.RpcContext;

@Component
public class AsyncInvocationServiceTest {

    @Reference(version = "1.0.0", async = true)// ע�����֧�ֵ����������첽���ã�����ʹ�������ļ�����
    private AsyncInvocationService service;
    
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
            new String[] { "classpath:applicationContext-client.xml" });
        context.start();      
        
        /*AsyncInvocationServiceTest test = (AsyncInvocationServiceTest) context.getBean("asyncInvocationServiceTest");
        System.out.println("�첽���÷�����" + test.service.asyncInvocation(2000));
        Future<String> future = RpcContext.getContext().getFuture(); // �õ����õ�Future���ã���������غ󣬻ᱻ֪ͨ�����õ���Future��
        System.out.println("ͬ�����÷����� " + test.service.syncInvocation(2000));
        
        try {
            String result = future.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }*/
        
        
        AsyncInvocationService service = (AsyncInvocationService) context.getBean("asyncInvocationService");
        System.out.println("�첽���÷�����" + service.asyncInvocation(2000));// ֱ�ӷ��ؿ�
        Future<String> future = RpcContext.getContext().getFuture(); // �õ����õ�Future���ã���������غ󣬻ᱻ֪ͨ�����õ���Future��
        System.out.println("ͬ�����÷����� " + service.syncInvocation(2000));
        
        try {
            String result = future.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
    
}
